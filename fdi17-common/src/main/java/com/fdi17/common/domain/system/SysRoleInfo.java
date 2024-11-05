package com.fdi17.common.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysRoleInfo 
 * @Description: 角色管理
 * @author jjt
 * @date 2023年3月25日 下午6:52:58
 */
@ApiModel(value="角色管理信息")
@Data
@TableName("SYS_ROLE_INFO")
public class SysRoleInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="角色id")
	private String roleId;
	
	@ApiModelProperty(value="角色名称")
	private String roleName;
	
	@ApiModelProperty(value="描述")
	private String description;
	
	@ApiModelProperty(value="人员生效主体 产险-PROP 寿险-LIFE 信保-SCI(Sunshine credit insurance) 集团-GROUP 产险信保-PCI(Property insurance and credit insurance) 综合金融-CF(Comprehensive finance) 融和医院 RH(Ronghe hospital)")
	private String company;
	
	@ApiModelProperty(value="PC/APP")
	private String loginMode;
	
	@ApiModelProperty(value="岗位属性标签开关（1-代表打开，0-代表关闭）")
	private String switchs;
	
	@ApiModelProperty(value="创建日期")
	private Date createDate;
	
	@ApiModelProperty(value="是否生效(1-正常 0-失效)")
	private String enabled;
	
	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;
	
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;
	
	//关联菜单
	@TableField(exist = false)
	@ApiModelProperty(value="权限列表")
	private List<String> authIdList;
}
