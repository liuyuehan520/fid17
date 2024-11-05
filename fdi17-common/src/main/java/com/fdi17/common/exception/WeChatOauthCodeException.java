package com.fdi17.common.exception;

/**
 * 企业微信权限验证码错误异常类
 * 
 * @author wuming
 */
public class WeChatOauthCodeException extends UserException {
	private static final long serialVersionUID = 1L;

	public WeChatOauthCodeException() {
		super("user.wechat.oauth.code.error", null);
	}
}
