package com.fdi17.common.exception;

/**
 * 验证码失效异常类
 * 
 * @author wuming
 */
public class CaptchaExpireException extends UserException {
	private static final long serialVersionUID = 1L;

	public CaptchaExpireException() {
		super("user.jcaptcha.expire", null);
	}
}
