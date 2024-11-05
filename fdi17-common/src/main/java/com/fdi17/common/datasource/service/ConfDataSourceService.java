package com.fdi17.common.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fdi17.api.common.vo.req.ConfDataSourceReqVo;
import com.fdi17.common.datasource.domain.ConfDataSource;

import java.util.List;

/**
 * @ClassName: ConfDataSourceService 
 * @Description: 数据源配置 
 * @author jjt
 * @date 2023年5月7日 上午11:19:29
 */
public interface ConfDataSourceService extends IService<ConfDataSource> {

    List<ConfDataSource> selectList(ConfDataSourceReqVo record);
}
