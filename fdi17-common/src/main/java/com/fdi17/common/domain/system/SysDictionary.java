package com.fdi17.common.domain.system;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysDictionary
 * @Description: 字典信息
 * @author jjt
 * @date 2023年3月25日 下午6:57:11
 */
@ApiModel(value="字典信息")
@Data
@TableName("SYS_DICTIONARY")
public class SysDictionary implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="分类属性:GRADE-职级,POST-岗位,(BUA00,BUC00,BUB00,BUV00,BUF00)-成本属性")
	private String dictType;

	@ApiModelProperty(value="字典指标")
	private String dictCode;

	@ApiModelProperty(value="字典描述")
	private String dictName;

	@ApiModelProperty(value="排序代码")
	private String sequenceCode;

	@ApiModelProperty(value="是否生效(1-正常 0-失效)")
	private String enabled;

	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;

	@ApiModelProperty(value="最近一次入库操作时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date loadDate;
}
