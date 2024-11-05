package com.fdi17.common.domain.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wangyang04
 * @since 2023-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_tableeditor_conf_table")
@ApiModel(value="SysTableeditorConf对象", description="")
public class SysTableeditorConfTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    @ApiModelProperty(value = "表名")
    @TableField("table_name")
    private String tableName;

    @ApiModelProperty(value = "数据源标识")
    @TableField("data_source")
    private String dataSource;

    @ApiModelProperty(value = "最近一次修改时间")
    @TableField("load_date")
    private LocalDateTime loadDate;

    @ApiModelProperty(value = "最近一次修改人域账号")
    @TableField("load_user")
    private String loadUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private String menuId;
    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_name")
    private String menuName;
    @ApiModelProperty(value = "数据状态")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "生成类型")
    @TableField("table_type")
    private String tableType;

    @ApiModelProperty(value = "描述")
    @TableField("remake")
    private String remake;

    @ApiModelProperty(value = "弹层标识")
    @TableField("flag")
    private String flag;
    @ApiModelProperty(value = "查询SQL")
    @TableField("create_sql")
    private String createSql;
    @ApiModelProperty(value = "追加SQL")
    @TableField("append_sql")
    private String appendSql;

    @ApiModelProperty(value = "对接系统")
    @TableField("dock")
    private String dock;

    @ApiModelProperty(value = "属组")
    @TableField("genus_group")
    private String genusGroup;
    @TableField("hide_flag")
    private String hideFlag;

}
