package com.fdi17.common.domain.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fdi17.common.domain.system.SysUserInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户身份权限
 *
 * @author wuhao01
 */
@Data
public class LoginUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户唯一标识
	 */
	private String token;

	/**
	 * 登录时间
	 */
	private Long loginTime;

	/**
	 * 过期时间
	 */
	private Long expireTime;

	/**
	 * 登录IP地址
	 */
	private String ipaddr;

	/**
	 * 登录地点
	 */
	private String loginLocation;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 登录方式
	 */
	private String mode;

	/**
	 * 用户信息
	 */
	private SysUserInfo user;

	public LoginUser() {
	}

	public LoginUser(SysUserInfo user) {
		this.user = user;

	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * 账户是否未过期,过期无法验证
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 指定用户是否解锁,锁定的用户无法进行身份验证
	 *
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 *
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否可用 ,禁用的用户不能身份验证
	 *
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
}
