package com.fdi17.common.domain.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysDeptInfo 
 * @Description: 部门管理
 * @author jjt
 * @date 2023年3月29日 下午5:26:01
 */
@ApiModel(value="部门信息")
@Data
@TableName("SYS_DEPT_INFO")
public class SysDeptInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="部门ID")
	private String deptId;

	@ApiModelProperty(value="描述")
	private String description;
	
	@ApiModelProperty(value="简短描述")
	private String deptName;
	
	@ApiModelProperty(value="公司人员所属主体, 产险-PROP 寿险-LIFE 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 融和医院 RH(Ronghe hospital)")
	private String company;
	
	@ApiModelProperty(value="上级部门id")
	private String parentDeptId;
	
	@ApiModelProperty(value="上级部门名称")
	private String parentDeptName;
	
	@ApiModelProperty(value="排序")
	private String sequenceCode;
	
	@ApiModelProperty(value="E-代表ehr部门，W-代表企业微信(WeChat)部门")
	private String deptType;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;

	@TableField(exist = false)
	private Boolean hasChild=false;
	
}
