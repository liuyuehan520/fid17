package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysAuthInfo 
 * @Description: 权限管理
 * @author jjt
 * @date 2023年3月25日 下午6:43:14
 */
@ApiModel(value="权限管理信息")
@Data
@TableName("SYS_AUTH_INFO")
public class SysAuthInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="权限ID")
	private String authId;
	
	@ApiModelProperty(value="权限名称")	
	private String authName;
	
	@ApiModelProperty(value="权限标识")
	private String authMark;
	
	@ApiModelProperty(value="PC/APP权限类别")
	private String loginMode;
	
	@ApiModelProperty(value="报表（R）、下载(D)、按钮(B)")
	private String authType;
	
	@ApiModelProperty(value="下载权限关联的菜单")
	private String menuId;
	
	@ApiModelProperty(value="人员生效主体 产险-PROP 寿险-LIFE 信保-SCI(Sunshine credit insurance) 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 综合金融-CF(Comprehensive finance) 融和医院 RH(Ronghe hospital)")
	private String company;
	
	@ApiModelProperty(value="是否生效(1-正常 0-失效)")
	private String enabled;
	
	@ApiModelProperty(value="创建日期")
	private Date createDate;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
	//关联菜单
	@TableField(exist = false)
	private List<String> menuIdList;

}
