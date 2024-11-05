package com.fdi17.common.datasource.service.impl;

import com.google.gson.Gson;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fdi17.common.domain.system.LoginUser;
import com.fdi17.common.utils.SecurityUtils;
import com.fdi17.common.datasource.domain.ConfSqlLog;
import com.fdi17.common.datasource.mapper.ConfSqlLogMapper;
import com.fdi17.common.datasource.service.ConfSqlLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: ConfSqlLogServiceImpl 
 * @Description: 执行SQL日志 
 * @author jjt
 * @date 2023年5月9日 下午6:10:03
 */
@Service
public class ConfSqlLogServiceImpl extends ServiceImpl<ConfSqlLogMapper,  ConfSqlLog> implements  ConfSqlLogService {

	/**
	 * 保存日志
	 */
	@Override
	public void saveLog(String sqlId, String sqlFlag, String querySql, Map<String, Object> param, 
			String statusCode, String message, Date startTime, Date endTime) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(param);
		ConfSqlLog sqlLog = new ConfSqlLog();
		sqlLog.setSqlId(sqlId);
		sqlLog.setSqlFlag(sqlFlag);
		sqlLog.setQuerySql(querySql);
		sqlLog.setParams(jsonString);
		sqlLog.setStatusCode(statusCode);
		sqlLog.setMessage(message);
		try{
			LoginUser loginUser = SecurityUtils.getLoginUser();
			sqlLog.setUsername(loginUser.getUsername());
		}catch (Exception e){
			sqlLog.setUsername("unknow");
		}
		sqlLog.setStartTime(startTime);
		sqlLog.setEndTime(endTime);
		sqlLog.setExpendMs(endTime.getTime() - startTime.getTime());
		sqlLog.setLoadDate(new Date());
		this.save(sqlLog);
	}
	
}
