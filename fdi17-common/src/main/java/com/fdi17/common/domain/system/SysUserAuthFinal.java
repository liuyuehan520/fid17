package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserAuthFinal 
 * @Description: 用户权限结果
 * @author jjt
 * @date 2023年3月30日 下午6:07:05
 */
@ApiModel(value="用户权限结果表")
@Data
@TableName("SYS_USER_AUTH_FINAL")
public class SysUserAuthFinal implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="OA账号")	
	private String username;
	
	@ApiModelProperty(value="权限ID")
	private String authId;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
