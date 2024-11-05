package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuPersonalize 
 * @Description: 个性菜单管理  
 * @author jjt
 * @date 2023年3月25日 下午6:52:07
 */
@ApiModel(value="个性菜单信息")
@Data
@TableName("SYS_MENU_PERSONALIZE")
public class SysMenuPersonalize  implements Serializable {

	private static final long serialVersionUID = 1L;

	@MppMultiId
	@ApiModelProperty(value="菜单ID")
	private String menuId;
	
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
	
	@MppMultiId
	@ApiModelProperty(value="人员生效主体,产险-PROP 寿险-LIFE 信保-SCI(Sunshine credit insurance) 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 综合金融-CF(Comprehensive finance) 融和医院 RH(Ronghe hospital)")
	private String portalCompany;
	
	@ApiModelProperty(value="是否左侧菜单(1-左侧 0-右侧)")
	private String position;
	
	@ApiModelProperty(value="更新/NEW图标（1-new 2-update 3-正常）")
	private String iconStatus;
	
	@ApiModelProperty(value="1-是，0-否 标签")
	private String labelStatus;
	
	@ApiModelProperty(value="标签样式")
	private String labelStyle;
	
	@ApiModelProperty(value="常用目录简称")
	private String shortName;
	
	@ApiModelProperty(value="是否隐藏")
	private String hideStatus;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
	@ApiModelProperty(value="是否标签栏展示/1-是，0-否")
	@TableField(value = "extend1")
	private String inTagViews;
	
	@ApiModelProperty(value="暂无预留字段")
	private String extend2;
	
	@ApiModelProperty(value="暂无预留字段")
	private String extend3;
	
	@ApiModelProperty(value="归属(1-内部 0-外部)")
	@TableField(exist = false)
    private String ascription;
	
	@ApiModelProperty(value="链接")
	@TableField(exist = false)
    private String link;
	
	@ApiModelProperty(value="链接ID")
	@TableField(exist = false)
    private String linkId;
	
	@ApiModelProperty(value="路由")
	@TableField(exist = false)
    private String path;
	
	@ApiModelProperty(value="组件")
	@TableField(exist = false)
    private String component;
	
	@ApiModelProperty(value="报表类型-M为菜单、R为报表")
	@TableField(exist = false)
	private String menuType;
	
	@ApiModelProperty(value="菜单属性-B为业务菜单、S为系统菜单、BI为SmartBI报表")
	@TableField(exist = false)
	private String menuAttr;
	
	@ApiModelProperty(value="前端调整顺序回传字段")
	@TableField(exist = false)
    private Integer order;
	
}
