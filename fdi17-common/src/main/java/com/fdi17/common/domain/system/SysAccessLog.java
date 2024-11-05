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
 * @ClassName: SysAccessLog 
 * @Description: 访问日志
 * @author jjt
 * @date 2023年3月25日 下午6:45:02
 */
@ApiModel(value="访问日志")
@Data
@TableName("SYS_ACCESS_LOG")
public class SysAccessLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="访问日志ID")
	private String accessId;

	@ApiModelProperty(value="访问用户账号")
	private String username;
	
	@ApiModelProperty(value="访问用户名称")
	private String fullName;
	
	@ApiModelProperty(value="访问时间")
	private Date accessTime;
	
	@ApiModelProperty(value="访问菜单ID")
	private String menuId;
	
	@ApiModelProperty(value="报表加载时长（单位：毫秒）")
	private String loadingTime;
	
	@ApiModelProperty(value="手机端与PC端，APP/PC")
	private String loginMode;
	
	@ApiModelProperty(value="公司人员所属主体 产险-PROP 寿险-LIFE 信保-SCI(Sunshine credit insurance) 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 综合金融-CF(Comprehensive finance) 融和医院 RH(Ronghe hospital)")
	private String company;
	
}
