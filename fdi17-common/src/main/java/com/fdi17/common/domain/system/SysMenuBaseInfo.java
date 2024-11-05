package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuBaseInfo 
 * @Description: 菜单基础信息配置管理 
 * @author jjt
 * @date 2023年3月25日 下午6:46:15
 */
@ApiModel(value="菜单基础信息")
@Data
@TableName("SYS_MENU_BASE_INFO")
public class SysMenuBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="报表ID")
	private String menuId;
	
	@ApiModelProperty(value="渠道ID")
	private String channelId;
	
	@ApiModelProperty(value="渠道名称")
	private String channelName;
	
	@ApiModelProperty(value="板块ID")
	private String plateId;
	
	@ApiModelProperty(value="板块名称")
	private String plateName;
	
	@ApiModelProperty(value="版主")
	private String users;
	
	@ApiModelProperty(value="更新类型")
	private String updateType;
	
	@ApiModelProperty(value="更新时间")
	private String updateDate;
	
	@ApiModelProperty(value="上线日期")
	private Date onlineDate;
	
	@ApiModelProperty(value="报表标题")
	private String title;
	
	@ApiModelProperty(value="数据来源")
	private String source;
	
	@ApiModelProperty(value="单位")
	private String unit;
	
	@ApiModelProperty(value="公司主体")
	private String company;
	
	@ApiModelProperty(value="管理部门代码")
	private String deptCode;
	
	@ApiModelProperty(value="管理部门名称")
	private String deptName;
	
	@ApiModelProperty(value="报表机构层级")
	private String comlevel;
	
	@ApiModelProperty(value="发布状态（报表基本信息保存后未已发布，报表复制后状态改为未发布）0未发布，1已发布，空无状态")
	private String publishState;
	
	@ApiModelProperty(value="更新状态（数据是否已更新）0未更新，1已更新, 空无状态")
	private String updateState;
	
	@ApiModelProperty(value="数据更新间隔，T+1,W+1,M+1M+2")
	private String updateRate;
	
	@ApiModelProperty(value="更新日期")
	private String updateDay;
	
	@ApiModelProperty(value="更新时间")
	private String updateTime;
	
	@ApiModelProperty(value="指标字典是否展示，0不展示，1展示, 空无状态")
	private String dictionaryShowState;
	
	@ApiModelProperty(value="是否开启消息提醒推送，手工录入及系统推送")
	private String noticeState;
	
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
