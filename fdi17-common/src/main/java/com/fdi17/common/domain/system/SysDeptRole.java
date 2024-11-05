package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysDeptRole 
 * @Description: 部门角色关系 
 * @author jjt
 * @date 2023年3月29日 下午5:36:55
 */
@ApiModel(value="部门角色关系")
@Data
@TableName("SYS_DEPT_ROLE")
public class SysDeptRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="部门id")	
	private String deptId;
	
	@ApiModelProperty(value="角色id")
	private String roleId;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
}
