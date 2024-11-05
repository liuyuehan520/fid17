package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuAuth 
 * @Description: 菜单权限关系信息
 * @author jjt
 * @date 2023年3月25日 下午6:57:32
 */
@ApiModel(value="菜单权限关系信息")
@Data
@TableName("SYS_MENU_AUTH")
public class SysMenuAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="菜单ID")	
	private String menuId;
	
	@ApiModelProperty(value="权限ID")
	private String authId;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
