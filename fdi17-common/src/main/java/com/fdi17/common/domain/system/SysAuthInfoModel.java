package com.fdi17.common.domain.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Description: 角色权限查询模型
 * @author wuhao01
 * @date 2023年4月19日 下午5:52:58
 */
@ApiModel(value="权限菜单查询模型")
@Data
public class SysAuthInfoModel {
	SysAuthInfo authInfo;
	private List<SysMenuInfo> menuList;
}
