package com.fdi17.common.datasource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ConfDataSource 
 * @Description: 数据源配置 
 * @author jjt
 * @date 2023年5月7日 上午11:08:48
 */
@Data
@TableName("conf_data_source")
public class ConfDataSource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="数据源ID")
    private String dataSourceId;

    @ApiModelProperty(value="数据源名称")
    private String dataSourceName;
    
    @ApiModelProperty(value="驱动程序类型")
    private String driverType;

    @ApiModelProperty(value="驱动程序类")
    private String driverClassName;

    @ApiModelProperty(value="连接字符串")
    private String url;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="验证查询SQL")
    private String validationQuerySql;
    
    @ApiModelProperty(value="入库用户")
    private String loadUser;

    @ApiModelProperty(value="入库时间")
    private Date loadDate;

    @ApiModelProperty(value="conf文件地址")
    private String addressConf;

    @ApiModelProperty(value="keytab文件地址")
    private String addressKeytab;

}
