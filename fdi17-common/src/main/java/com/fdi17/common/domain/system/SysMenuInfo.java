package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuInfo
 * @Description: 菜单管理接口
 * @author jjt
 * @date 2023年3月25日 下午6:47:38
 */
@ApiModel(value="基础菜单信息")
@Data
@TableName("SYS_MENU_INFO")
public class SysMenuInfo  implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="菜单ID")
	private String menuId;

	@ApiModelProperty(value="菜单描述")
	private String description;

	@ApiModelProperty(value="菜单名称")
	private String menuName;

	@ApiModelProperty(value="上级菜单")
	private String parentId;

	@ApiModelProperty(value="排序代码")
	private Integer sequenceCode;

	@ApiModelProperty(value="图标")
	private String iconUrl;

	@ApiModelProperty(value="菜单层级")
	private String menuLevel;

	@ApiModelProperty(value="是否生效(1-正常 0-失效)")
	private String enabled;

	@ApiModelProperty(value="是否左侧菜单(1-左侧 0-右侧)")
	private String position;

	@ApiModelProperty(value="更新/NEW图标（1-new 2-update 3-正常）")
	private String iconStatus;

	@ApiModelProperty(value="人员生效主体,产险-PROP 寿险-LIFE 信保-SCI(Sunshine credit insurance) 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 综合金融-CF(Comprehensive finance) 融和医院 RH(Ronghe hospital)")
	private String company;

	@ApiModelProperty(value="报表类型-M为菜单、R为报表")
	private String menuType;

	@ApiModelProperty(value="菜单属性-B为业务菜单、S为系统菜单、BI为SmartBI报表")
	private String menuAttr;

	@ApiModelProperty(value="1-是，0-否 标签")
	private String labelStatus;

	@ApiModelProperty(value="标签样式")
	private String labelStyle;

	@ApiModelProperty(value="PC/APP权限类别")
	private String loginMode;

	@ApiModelProperty(value="链接地址")
	private String linkId;

	@ApiModelProperty(value="创建日期")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;

	@ApiModelProperty(value="最近一次入库操作时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date loadDate;

	@ApiModelProperty(value="是否标签栏展示/1-是，0-否")
	@TableField(value = "extend1")
	private String inTagViews;

	@ApiModelProperty(value="暂无预留字段")
	private String extend2;

	@ApiModelProperty(value="暂无预留字段")
	private String extend3;

	@ApiModelProperty(value="状态 add-添加")
	@TableField(exist = false)
	private String status;

	@ApiModelProperty(value="归属(1-内部 0-外部)")
	@TableField(exist = false)
    private String ascription;

	@ApiModelProperty(value="链接名称")
	@TableField(exist = false)
    private String linkName;

	@ApiModelProperty(value="链接")
	@TableField(exist = false)
    private String link;

	@ApiModelProperty(value="路由")
	@TableField(exist = false)
    private String path;

	@ApiModelProperty(value="组件")
	@TableField(exist = false)
    private String component;

	@ApiModelProperty(value="子菜单")
	@TableField(exist = false)
    private List<SysMenuInfo> children;

}
