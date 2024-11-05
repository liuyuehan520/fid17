package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysDeptUser
 * @Description:部门用户关系
 * @author jjt
 * @date 2023年3月29日 下午5:30:58
 */
@ApiModel(value="部门用户关系信息")
@Data
@TableName("SYS_DEPT_USER")
public class SysDeptUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="用户ID")
	private String username;

	@ApiModelProperty(value="部门ID")
	private String deptId;

	@ApiModelProperty(value="是否主岗（1-主岗 0-兼岗）")
	private String emplRcd;

	@ApiModelProperty(value="S-代表系统用户，E-ehr用户，SHR-代表产险外勤shr用户")
	private String userType;

	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;

}
