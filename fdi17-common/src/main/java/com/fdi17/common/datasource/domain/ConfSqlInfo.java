package com.fdi17.common.datasource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ConfSqlInfo 
 * @Description: sql配置 
 * @author jjt
 * @date 2023年5月7日 上午11:09:07
 */
@Data
@TableName("CONF_SQL_INFO")
public class ConfSqlInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value="ID")
    private String sqlId;

    @ApiModelProperty(value="以【终端-主体-模块-名称-类型】组合为唯一标识，用【-】分割，全部大写。终端为PC/APP；主体为集团-GROUP/产险-PROP/寿险-LIFE/信保-SCI/产险信保-PCI/综合金融-CF/融和医院RH；类型为TABLE/CHART。示例 APP-GROUP-SCDB-ZBF-TABLE")
    private String sqlFlag;
    
    @ApiModelProperty(value="名称")
    private String sqlName;

    @ApiModelProperty(value="数据源ID")
    private String dataSourceId;

    @ApiModelProperty(value="查询sql")
    private String querySql;

    @ApiModelProperty(value="生效日期")
    private Date takeEffectDate;

    @ApiModelProperty(value="失效日期")
    private Date invalidDate;

    @ApiModelProperty(value="主体公司")
    private String company;

    @ApiModelProperty(value="菜单ID")
    private String menuId;
    
    @ApiModelProperty(value="平台")
    private String platform;
    
    @ApiModelProperty(value="页面元素ID")
    private String domDivId;
    
    @ApiModelProperty(value="备注")
    private String remark;
    
    @ApiModelProperty(value="入库用户")
    private String loadUser;

    @ApiModelProperty(value="入库时间")
    private Date loadDate;

}
