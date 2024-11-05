package com.fdi17.common.domain.system;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysMenuVO 
 * @Description: 前端菜单结构 
 * @author jjt
 * @date 2023年4月1日 下午3:25:50
 */
@ApiModel(value="前端菜单结构 ")
@Data
public class SysMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="菜单名称")
	private String name;
	
	@ApiModelProperty(value="路由")
	private String path;
	
	@ApiModelProperty(value="组件")
	private String component;
	
	@ApiModelProperty(value="是否隐藏")
	private Boolean hidden;
	
	@ApiModelProperty(value="排序代码")
	private Integer sequenceCode;
	
	@ApiModelProperty(value="meta")
	private SysMenuMeta meta;
	
	@ApiModelProperty(value="子菜单")
	@TableField(exist = false)
    private List<SysMenuVO> children;

}
