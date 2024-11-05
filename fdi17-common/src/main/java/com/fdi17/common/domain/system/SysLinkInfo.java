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
 * @ClassName: SysLinkInfo 
 * @Description: 系统链接地址
 * @author jjt
 * @date 2023年3月25日 下午6:45:02
 */
@ApiModel(value="链接地址信息")
@Data
@TableName("SYS_LINK_INFO")
public class SysLinkInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="ID")
	private String linkId;

	@ApiModelProperty(value="链接")
	private String link;
	
	@ApiModelProperty(value="名称")
	private String name;
	
	@ApiModelProperty(value="地址")
	private String path;
	
	@ApiModelProperty(value="组件")
	private String component;
	
	@ApiModelProperty(value="站点")
	private String site;
	
	@ApiModelProperty(value="密钥")
	private String syspwd;
	
	@ApiModelProperty(value="盐值")
	private String salt;
	
	@ApiModelProperty(value="归属(1-内部 0-外部)")
	private String ascription;
	
	@ApiModelProperty(value="类型标识")
	private String type;
	
	@ApiModelProperty(value="是否生效(1-正常 0-失效)")
	private String enabled;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
	@ApiModelProperty(value="扩展字段")
	private String extend1;
	
	@ApiModelProperty(value="扩展字段")
	private String extend2;
	
	@ApiModelProperty(value="扩展字段")
	private String extend3;
	
}
