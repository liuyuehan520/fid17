package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserInfo
 * @Description:用户管理
 * @author jjt
 * @date 2023年3月25日 下午6:54:57
 */
@ApiModel(value="用户信息")
@Data
@TableName("SYS_USER_INFO")
public class SysUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value="登录用户")
	private String username;

	@ApiModelProperty(value="人员所属主体, 产险-PROP 寿险-LIFE 集团-GROUP")
	private String company;

	@ApiModelProperty(value="用户姓名")
	private String fullName;

	@ApiModelProperty(value="用户职级")
	private String grade;

	@ApiModelProperty(value="职级序列")
	private String gradeLevel;

	@ApiModelProperty(value="手机号码")
	private String phoneNumber;

	@ApiModelProperty(value="邮箱地址")
	private String email;

	@ApiModelProperty(value="密码")
	private String password;

	@ApiModelProperty(value="加密盐")
	private String salt;

	@ApiModelProperty(value="性别")
	private String sex;

	@ApiModelProperty(value="S-代表系统用户")
	private String userType;

	@ApiModelProperty(value="人员个性化主体, 产险-PROP 寿险-LIFE 集团-GROUP")
	private String portalCompany;

	@ApiModelProperty(value="是否生效 (1-正常 0-失效)")
	private String enabled;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="用户信息创建日期")
	private Date createDate;

	@ApiModelProperty(value="PC端sessionid")
	private String sessionId;

	@ApiModelProperty(value="手机端session")
	private String appSessionId;

	@ApiModelProperty(value="最近一次入库操作用户")
	private String loadUser;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="最近一次入库操作时间")
	private Date loadDate;

	@ApiModelProperty(value="主题色")
	private String theme;

	@ApiModelProperty(value="扩展字段1")
	private String extend1;

	@ApiModelProperty(value="扩展字段2")
	private String extend2;

	@ApiModelProperty(value="扩展字段3")
	private String extend3;

	//关联角色
	@TableField(exist = false)
	private List<String> roleIdList = new ArrayList<String>();

	//用户部门
	@TableField(exist = false)
	private List<SysDeptUser> deptList = new ArrayList<SysDeptUser>();

	//用户机构
	@TableField(exist = false)
	private List<SysComcodeInfo> comList = new ArrayList<SysComcodeInfo>();
}
