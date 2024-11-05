package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysTrajectoryLog 
 * @Description: 用户行为日志 
 * @author jjt
 * @date 2023年5月5日 下午2:47:22
 */
@ApiModel(value="用户行为日志")
@Data
@TableName("SYS_TRAJECTORY_LOG")
public class SysTrajectoryLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="日志ID")
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	
	@ApiModelProperty(value="系统编号")
	private String systemCode;
	
	@ApiModelProperty(value="用户")
	private String username;
	
	@ApiModelProperty(value="报表ID")
	private String reportId;
	
	@ApiModelProperty(value="事件动作")
	private String type;
	
	@ApiModelProperty(value="事件类型")
	private String subType;
	
	@ApiModelProperty(value="事件名称")
	private String name;
	
	@ApiModelProperty(value="事件值")
	private String value;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@ApiModelProperty(value="用户操作时间")
	private Date timestamp;
	
	@ApiModelProperty(value="用户token")
	private String token;

	@ApiModelProperty(value="加载时间")
	private Date loadDate;
	
}
