package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysComcodeInfo
 * @Description: 机构信息
 * @author jjt
 * @date 2023年6月23日 上午11:27:17
 */
@ApiModel(value = "机构信息")
@Data
@TableName("SYS_COMODE_INFO")
public class SysComcodeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "所属主体")
	private String company;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "机构全称")
	private String fullName;

	@ApiModelProperty(value = "机构名称")
	private String shortName;

	@ApiModelProperty(value = "机构层级")
	private String comlevel;

	@ApiModelProperty(value = "上级机构")
	private String parentComcode;

	@ApiModelProperty(value = "机构代码")
	private String comcode;

	@ApiModelProperty(value = "手机端简称")
	private String appShortName;

	@ApiModelProperty(value = "最近一次入库操作用户")
	private String loadUser;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "最近一次入库操作时间")
	private Date loadDate;

}
