package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserRoleFinal 
 * @Description: 用户角色结果 
 * @author jjt
 * @date 2023年3月30日 下午6:07:34
 */
@ApiModel(value="用户角色结果表")
@Data
@TableName("SYS_USER_ROLE_FINAL")
public class SysUserRoleFinal implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="OA账号")	
	private String username;
	
	@ApiModelProperty(value="角色ID")
	private String roleId;
	
	@ApiModelProperty(value="直接授予-A 直接继承-B 间接继承-C")
	private String bloodFlag;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
