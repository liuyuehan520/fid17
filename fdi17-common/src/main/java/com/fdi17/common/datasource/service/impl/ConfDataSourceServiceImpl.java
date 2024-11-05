package com.fdi17.common.datasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fdi17.api.common.utls.CheckUtil;
import com.fdi17.api.common.vo.req.ConfDataSourceReqVo;
import com.fdi17.common.datasource.domain.ConfDataSource;
import com.fdi17.common.datasource.mapper.ConfDataSourceMapper;
import com.fdi17.common.datasource.service.ConfDataSourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ConfDataSourceServiceImpl 
 * @Description: 数据源配置
 * @author jjt
 * @date 2023年5月7日 上午11:10:03
 */
@Service
public class ConfDataSourceServiceImpl extends ServiceImpl<ConfDataSourceMapper, ConfDataSource> implements ConfDataSourceService {

    @Override
    public List<ConfDataSource> selectList(ConfDataSourceReqVo record) {
        LambdaQueryWrapper<ConfDataSource> entity = findbOrderByAscSequenceCodeWrapper(record);
        return this.list(entity);
    }

    private LambdaQueryWrapper<ConfDataSource> findbOrderByAscSequenceCodeWrapper(ConfDataSourceReqVo record) {
        LambdaQueryWrapper<ConfDataSource> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CheckUtil.isNotEmpty(record.getDataSourceId()), ConfDataSource::getDataSourceId, record.getDataSourceId());
        return queryWrapper;
    }

}
