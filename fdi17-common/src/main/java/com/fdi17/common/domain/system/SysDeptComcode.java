package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysDeptComcode 
 * @Description: 部门机构关系 
 * @author jjt
 * @date 2023年8月8日 下午2:26:23
 */
@ApiModel(value = "部门机构")
@Data
@TableName("SYS_DEPT_COMCODE")
public class SysDeptComcode implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "部门ID")
	private String deptId;

	@ApiModelProperty(value = "机构代码")
	private String comcode;
	
	@ApiModelProperty(value = "类型-S-代表系统，E-代表EHR")
	private String comcodeType;
	
	@ApiModelProperty(value = "最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value = "最近一次入库操作时间")
	private Date loadDate;

}
