package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserMenuFinal 
 * @Description: 用户菜单关系信息
 * @author jjt
 * @date 2023年3月25日 下午6:58:21
 */
@ApiModel(value="用户菜单关系信息")
@Data
@TableName("SYS_USER_MENU_FINAL")
public class SysUserMenuFinal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="菜单ID")
	private String username;
	
	@ApiModelProperty(value="菜单ID")
	private String menuId;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
