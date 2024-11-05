package com.fdi17.common.datasource.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cyp
 * @date 2023/5/28
 * @description 数据源管理
 */
@Slf4j
public class DataSourceContextHolder {
	/**
	 * 线程级别的私有变量
	 */
	private static final ThreadLocal<String> CONTEXTHOLDER = new ThreadLocal<>();

	/**
	 * 切换数据源
	 */
	public static void setDataSource(String datasourceId) {
		CONTEXTHOLDER.set(datasourceId);
		log.info("已切换到数据源:{}", datasourceId);
	}
	
	/**
	 * 获取数据源
	 */
	public static String getDataSource() {
		return CONTEXTHOLDER.get();
	}

	/**
	 * 删除数据源
	 */
	public static void removeDataSource() {
		CONTEXTHOLDER.remove();
		log.info("已切换到主数据源");
	}
}
