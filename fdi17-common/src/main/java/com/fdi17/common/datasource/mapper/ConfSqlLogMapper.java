package com.fdi17.common.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdi17.common.datasource.domain.ConfSqlLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ConfSqlLogMapper 
 * @Description: 执行SQL日志 
 * @author jjt
 * @date 2023年5月9日 下午6:09:15
 */
@Mapper
public interface ConfSqlLogMapper extends BaseMapper<ConfSqlLog> {
	
}
