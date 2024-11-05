package com.fdi17.common.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdi17.common.datasource.domain.ConfDataSource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ConfDataSourceMapper 
 * @Description: 数据源配置
 * @author jjt
 * @date 2023年5月7日 上午11:09:36
 */
@Mapper
public interface ConfDataSourceMapper extends BaseMapper<ConfDataSource> {
	
}
