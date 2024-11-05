package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserComcodeFinal
 * @Description: 用户机构关系
 * @author jjt
 * @date 2023年6月23日 上午11:26:52
 */
@ApiModel(value = "用户机构")
@Data
@TableName("SYS_USER_COMCODE_FINAL")
public class SysUserComcodeFinal implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户账号")
	private String username;

	@ApiModelProperty(value = "机构代码")
	private String comcode;

	@ApiModelProperty(value = "机构层级")
	private String comlevel;

	@ApiModelProperty(value = "是否是默认机构（1-默认，0-不是默认）")
	private String defaultStatus;

	@ApiModelProperty(value = "是否编辑过默认机构（1-编辑过，0-没有编辑过）")
	private String editStatus;

	@ApiModelProperty(value = "所属主体")
	private String company;
	
	@ApiModelProperty(value = "直接授予-A 直接继承-B 间接继承-C")
	private String bloodFlag;

	@ApiModelProperty(value = "最近一次入库操作用户")
	private String loadUser;

	@ApiModelProperty(value = "最近一次入库操作时间")
	private Date loadDate;

}
