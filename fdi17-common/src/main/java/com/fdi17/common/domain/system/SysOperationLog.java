package com.fdi17.common.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysOperationLog 
 * @Description: 操作日志 
 * @author jjt
 * @date 2023年4月12日 下午4:08:39
 */
@ApiModel(value="操作日志信息")
@Data
@TableName("SYS_OPERATION_LOG")
public class SysOperationLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="操作日志ID")
	@TableId(type = IdType.ASSIGN_UUID)
	private String operationLogId;
	
	@ApiModelProperty(value="用户")
	private String username;
	
	@ApiModelProperty(value="操作类型")
	private String operationType;
	
	@ApiModelProperty(value="操作对象")
	private String object;
	
	@ApiModelProperty(value="操作时间")
	private Date operationTime;
	
	@ApiModelProperty(value="操作描述")
	private String operationDesc;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
}
