package com.fdi17.common.datasource.service;

import com.fdi17.common.constant.BeaseConstant;
import com.fdi17.common.datasource.config.DataSourceContextHolder;
import com.fdi17.common.datasource.config.DynamicRoutingDataSource;
import com.fdi17.common.datasource.domain.ConfDataSource;
import com.fdi17.common.datasource.mapper.ConfDataSourceMapper;
import com.fdi17.common.datasource.mapper.ConfSqlInfoMapper;
import com.fdi17.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivilegedAction;
import java.sql.*;
import java.util.*;

/**
 * @author lfk
 * @ClassName: ExecuteSqlService
 * @Description: 执行sql -- 多数据源
 * @date 2024年4月10日 上午11:00:02
 */
@Slf4j
@Service
public class ExecuteSqlService {

    @Autowired
    private ConfDataSourceMapper confDataSourceMapper;

    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Autowired
    private ConfSqlInfoMapper confSqlInfoMapper;

    /**
     * @param dataSourceId（数据源Id）
     * @param sqlText（sql文本）
     * @param param（sql参数）
     * @return List<LinkedHashMap < String, Object>> 返回类型
     * @throws
     * @Title: executeSql
     * @Description: 执行sql -- 多数据源
     * @author jjt
     * @date 2023年4月3日 上午11:02:55
     */
    public List<LinkedHashMap<String, Object>> executeSql(String dataSourceId, String sqlText,
                                                          Map<String, Object> param) {
        List<LinkedHashMap<String, Object>> resultData = new ArrayList<LinkedHashMap<String, Object>>();

        if (StringUtils.isEmpty(sqlText)) {
            return resultData;
        }
        try {
            // 获取数据源配置
            ConfDataSource dataSource = confDataSourceMapper.selectById(dataSourceId);
            if (null == dataSource) {
                throw new ServiceException(dataSourceId + "未配置相关数据源!");
            }
            // 切换数据源
            dynamicRoutingDataSource.checkCreateDataSource(dataSource);
            DataSourceContextHolder.setDataSource(dataSource.getDataSourceId());
            // 获取执行sql
//            if (StringUtils.isNotEmpty(sqlText)) {
//            resultData = confSqlInfoMapper.executeSql(sqlText, param);
//            }
            // 获取执行sql
            String[] sqls = sqlText.split(BeaseConstant.RRX_DELIMITER);
            for (String sql : sqls) {
                // 包含call或者CALL的sql语句，才进行获取存储过程out值处理
                if (sql.contains("call") || sql.contains("CALL")) {
                    Map<String, Object> resultMap = new HashMap<>(param);
                    resultMap.put("sql", sql);
                    confSqlInfoMapper.executeProcedureSql(resultMap);
                    // 获取out返回值
                    resultMap.keySet().removeIf(param::containsKey);
                    resultMap.remove("sql");
                    if (resultMap.size() > 0) {
                        resultData.add(new LinkedHashMap<>(resultMap));
                    }
                } else {
                    resultData.addAll(confSqlInfoMapper.executeSql(sql, param));
                }
            }
            DataSourceContextHolder.removeDataSource();
        } catch (Exception e) {
            log.error("executeSql数据库sql执行有异常：{}", e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            DataSourceContextHolder.removeDataSource();
        }
        return resultData;
    }

    public List<LinkedHashMap<String, Object>> executeHiveSql(String dataSourceId, String sqlText,
                                                              Map<String, Object> param, Boolean getHeader) {
        System.out.println("executeHiveSql success.");
        List<LinkedHashMap<String, Object>> resultData = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            // 获取数据源配置
            ConfDataSource dataSource = confDataSourceMapper.selectById(dataSourceId);
            if (null == dataSource) {
                throw new ServiceException(dataSourceId + "未配置相关数据源!");
            }
            Properties prop = new Properties();
            prop.put("user", dataSource.getUsername());
            prop.put("password", dataSource.getAddressConf() + "&&" + dataSource.getAddressKeytab());
            if (param != null && param.containsKey("tablename")) {
                sqlText = sqlText.replace("#{tablename}", param.get("tablename") + "");
            }
            sqlText = sqlText.replace(";", "");
            resultData = RunHiveSql(dataSource.getUrl(), prop, sqlText, getHeader);

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return resultData;
    }

    private List<LinkedHashMap<String, Object>> RunHiveSql(String connUrl, Properties prop, String querySql, Boolean getHeader) {
        List<LinkedHashMap<String, Object>> resultData = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            System.setProperty("sun.security.krb5.debug", "true");
            System.setProperty("java.security.krb5.realm", prop.get("user").toString().split("@")[1]);
            System.setProperty("java.security.krb5.kdc", prop.get("user").toString().split("@")[0].split("/")[1]);
            System.setProperty("java.security.krb5.conf", prop.get("password").toString().split("&&")[0].replace("||", "\\\\"));
            org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
            conf.set("hadoop.security.authentication", "kerberos");

            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab(prop.get("user") + "", prop.get("password").toString().split("&&")[1].replace("||", "\\\\"));
            UserGroupInformation loginUser = UserGroupInformation.getLoginUser();
            resultData = loginUser.doAs((PrivilegedAction<List<LinkedHashMap<String, Object>>>) () -> {
                try {
                    System.out.println("Hive数据库连接成功");
                    Class.forName("org.apache.hive.jdbc.HiveDriver");
                    try (Connection connection = DriverManager.getConnection(connUrl)) {
                        System.out.println("Connection连接成功");
                        try (Statement statement = connection.createStatement()) {
                            System.out.println("querySql语句:" + querySql);
                            return convertList(statement.executeQuery(querySql), getHeader);
                        }
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Hive数据库连接失败:" + e.getMessage());
                    e.printStackTrace();
                }
                return new ArrayList<LinkedHashMap<String, Object>>();
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return resultData;
    }

    private List<LinkedHashMap<String, Object>> convertList(ResultSet rs, Boolean getHeader) throws SQLException {
        List<LinkedHashMap<String, Object>> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount(); // Map rowData;
        System.out.println("columnCount值:" + columnCount);
        while (rs.next()) { // rowData = new HashMap(columnCount);
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(md.getColumnName(i).replace("querylinksql.", ""), rs.getObject(i));
            }
            list.add(map);
        }
        if (getHeader && !rs.next()) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(md.getColumnName(i).replace("querylinksql.", ""), md.getColumnTypeName(i));
            }
            list.add(map);
        }
        System.out.println("list个数:" + list.size());
        return list;
    }

    /**
     * @param dataSourceId（数据源Id）
     * @param sqlText（sql文本）
     * @param param（sql参数）
     * @return List<LinkedHashMap < String, Object>> 返回类型
     * @throws
     * @Title: executeSql
     * @Description: 执行sql -- 多数据源
     * @author jjt
     * @date 2023年4月3日 上午11:02:55
     */
    public List<LinkedHashMap<String, Object>> getHeaderBySql(String dataSourceId, String sqlText,
                                                              Map<String, Object> param) {
        List<LinkedHashMap<String, Object>> resultData = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            // 获取数据源配置
            ConfDataSource dataSource = confDataSourceMapper.selectById(dataSourceId);
            if (null == dataSource) {
                throw new ServiceException(dataSourceId + "未配置相关数据源!");
            }
            // 切换数据源
            dynamicRoutingDataSource.checkCreateDataSource(dataSource);
            DataSourceContextHolder.setDataSource(dataSource.getDataSourceId());
            // 获取执行sql
            if (StringUtils.isNotEmpty(sqlText)) {
                Connection connection = dynamicRoutingDataSource.getConnection();
                Statement statement = connection.createStatement();
                if (param != null && param.size() > 0) {
                    Iterator iterator = param.keySet().iterator();
                    while (iterator.hasNext()) {
                        String next = iterator.next().toString();
                        if (null == param.get(next)) {
                            sqlText = sqlText.replaceAll(":" + next, null);
                        } else {
                            sqlText = sqlText.replaceAll(":" + next, param.get(next).toString());
                        }
                    }
                }
                ResultSet resultSet = statement.executeQuery(sqlText);
                ResultSetMetaData metaData = resultSet.getMetaData();
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                int numColumns = metaData.getColumnCount();
                for (int i = 0; i < numColumns; i++) {
                    map.put(metaData.getColumnName(i + 1).replace("querylinksql.", ""), metaData.getColumnTypeName(i + 1));
                }
                resultData.add(map);
            }
            DataSourceContextHolder.removeDataSource();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            DataSourceContextHolder.removeDataSource();
        }
        return resultData;
    }
}
