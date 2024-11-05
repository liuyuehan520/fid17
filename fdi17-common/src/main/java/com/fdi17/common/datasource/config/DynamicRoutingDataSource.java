package com.fdi17.common.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.fdi17.common.datasource.domain.ConfDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

/**
 * @author cyp
 * @date 2023/5/28
 * @description 数据源管理
 */
@Slf4j
@Data
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	private boolean debug = true;
	/**
	 * 存储我们注册的数据源
	 */
	private volatile Map<Object, Object> customDataSources;

	@Override
	protected Object determineCurrentLookupKey() {
		String datasourceId = DataSourceContextHolder.getDataSource();
		if (!StringUtils.isEmpty(datasourceId)) {
			Map<Object, Object> map = this.customDataSources;
			if (map.containsKey(datasourceId)) {
				log.info("当前数据源是：{}", datasourceId);
			} else {
				log.info("不存在数据源：{}", datasourceId);
				return null;
			}
		} else {
			log.info("当前是默认数据源");
		}
		return datasourceId;
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> param) {

		super.setTargetDataSources(param);
		this.customDataSources = param;
	}


	/**
	 * @Description: 检查数据源是否已经创建
	 * @author
	 * @date
	 * @param dataSource
	 */
	public void checkCreateDataSource(ConfDataSource dataSource) {
		String datasourceId = dataSource.getDataSourceId();
		Map<Object, Object> map = this.customDataSources;
		DruidPooledConnection connection = null;
		boolean flag = true;
		try {
			if (map.containsKey(datasourceId)) {
				// 这里检查一下之前创建的数据源，现在是否连接正常
				DruidDataSource druidDataSource = (DruidDataSource) map.get(datasourceId);
				connection = druidDataSource.getConnection();
			}
		} catch (Exception e) {
			// 抛异常了说明连接失效吗，则删除现有连接
			log.error(e.getMessage());
			flag = false;
			deleteDataSources(datasourceId);
		} finally {
			// 如果连接正常记得关闭
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		if(!map.containsKey(datasourceId) || !flag) {
			createDataSource(dataSource);
		}
	}

	/**
	 * base64解码
	 * @param decode
	 * @return
	 */
	private String decodeBase64(String decode){
		final Base64.Decoder decoder = Base64.getDecoder();
		try {
			String encode = new String(decoder.decode(decode), "UTF-8");
			log.info("base64解码，decode:{}, encode:{}", decode, encode);
			return encode;
		} catch (Exception e) {
			log.info("base64解码异常", e);
			return decode;
		}
	}

	/**
	 * @Description: 创建数据源
	 * @author
	 * @date
	 * @param dataSource
	 */
	private void createDataSource(ConfDataSource dataSource) {

		try {
			String base64PWD = decodeBase64(dataSource.getPassword());
			Connection connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(),
					base64PWD);
			if (connection == null) {
				log.error("数据源配置有错误，DataSource：{}", dataSource);
			} else {
				connection.close();
			}
			DruidDataSource druidDataSource = new DruidDataSource();
			druidDataSource.setName(dataSource.getDataSourceId());
			druidDataSource.setDriverClassName(dataSource.getDriverClassName());
			druidDataSource.setUrl(dataSource.getUrl());
			druidDataSource.setUsername(dataSource.getUsername());
			druidDataSource.setPassword(base64PWD);
			// 初始连接数
			druidDataSource.setInitialSize(20);
			// 最小连接池数量
			druidDataSource.setMinIdle(20);
			// 最大连接池数量
			druidDataSource.setMaxActive(100);
			// 配置获取连接等待超时时间  毫秒
			druidDataSource.setMaxWait(6000);
			//缓存通过以下两个方法发起的SQL:
			druidDataSource.setPoolPreparedStatements(true);
			//每个连接最多缓存多少个SQL
			druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
			//检查空闲连接的频率，单位毫秒, 非正整数时表示不进行检查
			druidDataSource.setTimeBetweenEvictionRunsMillis(-1);
			//池中某个连接的空闲时长达到 N 毫秒后, 连接池在下次检查空闲连接时，将回收该连接,要小于防火墙超时设置
			druidDataSource.setMinEvictableIdleTimeMillis(300000);
			//当程序请求连接，池在分配连接时，是否先检查该连接是否有效。(高效)
			druidDataSource.setTestWhileIdle(true);
			// 程序 申请 连接时,进行连接有效性检查（低效，影响性能）
			druidDataSource.setTestOnBorrow(false);
			//程序 返还 连接时,进行连接有效性检查（低效，影响性能）
			druidDataSource.setTestOnReturn(false);
			// 要求程序从池中get到连接后, N 秒后必须close,否则druid 会强制回收该连接,不管该连接中是活动还是空闲, 以防止进程不会进行close而霸占连接。
			druidDataSource.setRemoveAbandoned(true);
			// 设置druid 强制回收连接的时限，当程序从池中get到连接开始算起，超过此值后，druid将强制回收该连接，单位秒。
			// 结合业务来看，存在jpa极大事务；不好设置 暂时为设置两分钟
			druidDataSource.setRemoveAbandonedTimeout(60000);
			//当druid强制回收连接后，是否将stack trace 记录到日志中
			druidDataSource.setLogAbandoned(true);
			//获取连接验证sql
			druidDataSource.setValidationQuery(dataSource.getValidationQuerySql());
			druidDataSource.init();
			this.customDataSources.put(dataSource.getDataSourceId(), druidDataSource);
			// 将map赋值给父类的TargetDataSources
			setTargetDataSources(this.customDataSources);
			// 将TargetDataSources中的连接信息放入resolvedDataSources管理
			super.afterPropertiesSet();

		} catch (Exception e) {
			log.error("数据源创建失败", e);
//			throw new ServiceException("数据源创建失败");
		}
	}

	/**
	 * @Description: 删除数据源
	 *
	 * @param datasourceId
	 */
	private void deleteDataSources(String datasourceId) {
		Map<Object, Object> map = this.customDataSources;
		Set<DruidDataSource> druidDataSourceInstances = DruidDataSourceStatManager.getDruidDataSourceInstances();
		for (DruidDataSource dataSource : druidDataSourceInstances) {
			if (datasourceId.equals(dataSource.getName())) {
				map.remove(datasourceId);
				// 从实例中移除当前dataSource
				DruidDataSourceStatManager.removeDataSource(dataSource);
				// 将map赋值给父类的TargetDataSources
				setTargetDataSources(map);
				// 将TargetDataSources中的连接信息放入resolvedDataSources管理
				super.afterPropertiesSet();
			}
		}
	}

}
