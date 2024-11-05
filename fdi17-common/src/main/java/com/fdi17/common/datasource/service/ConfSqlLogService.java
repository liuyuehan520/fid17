package com.fdi17.common.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fdi17.common.datasource.domain.ConfSqlLog;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: ConfSqlLogService 
 * @Description: 执行SQL日志 
 * @author jjt
 * @date 2023年5月9日 下午6:09:36
 */
public interface ConfSqlLogService extends IService<ConfSqlLog>{
	
	/**
	 * @Title: saveLog 
	 * @Description: 保存日志 
	 * @author jjt
	 * @date 2023年5月9日 下午6:09:43
	 * @param @param sqlId
	 * @param @param sqlFlag
	 * @param @param querySql
	 * @param @param param
	 * @param @param statusCode
	 * @param @param message
	 * @param @param startTime
	 * @param @param endTime    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	void saveLog(String sqlId, String sqlFlag, String querySql, Map<String, Object> param, 
			String statusCode, String message, Date startTime, Date endTime);
}
