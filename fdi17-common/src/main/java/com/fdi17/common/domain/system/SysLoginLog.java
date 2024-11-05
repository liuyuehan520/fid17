package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysLoginLog 
 * @Description: 登录日志信息
 * @author jjt
 * @date 2023年3月25日 下午7:09:47
 */
@ApiModel(value="登录日志信息")
@Data
@TableName("SYS_LOGIN_LOG")
public class SysLoginLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="登录日志ID")
	@TableId(type = IdType.ASSIGN_UUID)
	private String loginLogId;
	
	@ApiModelProperty(value="登录用户ip地址")
	private String loginIp;
	
	@ApiModelProperty(value="登出时间")
	private Date endTime;
	
	@ApiModelProperty(value="登陆时间")
	private Date startTime;
	
	@ApiModelProperty(value="登录人")
	private String username;
	
	@ApiModelProperty(value="部门")
	private String dept;
	
	@ApiModelProperty(value="session的ID")
	private String sessionId;
	
	@ApiModelProperty(value="职级")
	private String grade;
	
	@ApiModelProperty(value="登录方式 OA跳转")
	private String mode;
	
	@ApiModelProperty(value="PC端||移动端")
	private String loginMode;
	
	@ApiModelProperty(value="浏览器")
	private String browser;
	
	@ApiModelProperty(value="操作系统")
	private String os;
	
	@ApiModelProperty(value="服务器ip")
	private String serverIp;
	
	@ApiModelProperty(value="登录状态")
	private String status;
	
	@ApiModelProperty(value="消息")
	private String message;
	
}
