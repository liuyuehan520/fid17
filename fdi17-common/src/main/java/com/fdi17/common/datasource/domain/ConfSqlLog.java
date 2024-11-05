package com.fdi17.common.datasource.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ConfSqlLog 
 * @Description: 执行SQL日志 
 * @author jjt
 * @date 2023年5月9日 下午6:08:38
 */
@Data
@TableName("CONF_SQL_LOG")
public class ConfSqlLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="sql id")
    private String sqlId;

    @ApiModelProperty(value="sql标识")
    private String sqlFlag;
    
    @ApiModelProperty(value="执行sql")
    private String querySql;
    
    @ApiModelProperty(value="参数")
    private String params;
    
    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="开始时间")
    private Date startTime;

    @ApiModelProperty(value="结束时间")
    private Date endTime;

    @ApiModelProperty(value="消耗毫秒")
    private Long expendMs;
    
    @ApiModelProperty(value="状态码")
    private String statusCode;
    
    @ApiModelProperty(value="信息")
    private String message;

    @ApiModelProperty(value="入库时间")
    private Date loadDate;

}
