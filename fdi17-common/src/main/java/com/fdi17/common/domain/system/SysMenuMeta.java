package com.fdi17.common.domain.system;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuMeta 
 * @Description: 菜单meta信息
 * @author jjt
 * @date 2023年4月1日 下午3:24:01
 */
@ApiModel(value="菜单meta信息")
@Data
public class SysMenuMeta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="菜单ID")
	private String menuId;
	
	@ApiModelProperty(value="菜单名称")
	private String title;
	
	@ApiModelProperty(value="菜单属性")
	private String menuAttr;
	
	@ApiModelProperty(value="上级菜单")
	private String parentId;
	
	@ApiModelProperty(value="图标")
	private String iconUrl;
	
	@ApiModelProperty(value="是否左侧菜单(1-左侧 0-右侧)")
	private String position;
	
	@ApiModelProperty(value="链接地址")
	private String link;
	
	@ApiModelProperty(value="链接ID")
	private String linkId;
	
	@ApiModelProperty(value="归属(1-内部 0-外部)")
	private String ascription;
	
	@ApiModelProperty(value="是否标签栏展示/1-是，0-否")
	private String inTagViews;
	
	@ApiModelProperty(value="默认报表")
	private String theDefault;
	
	@ApiModelProperty(value="全路径path")
	private String activePath;
	
}
