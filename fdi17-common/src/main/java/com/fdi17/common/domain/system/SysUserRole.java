package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserRole 
 * @Description: 用户角色关系信息 
 * @author jjt
 * @date 2023年3月25日 下午6:58:43
 */
@ApiModel(value="用户角色关系信息")
@Data
@TableName("SYS_USER_ROLE")
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="用户账号")	
	private String username;
	
	@ApiModelProperty(value="角色ID")
	private String roleId;
	
	@ApiModelProperty(value="关系来源(系统-SYSTEM，自助授权-AUTO)")
	private String source;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
