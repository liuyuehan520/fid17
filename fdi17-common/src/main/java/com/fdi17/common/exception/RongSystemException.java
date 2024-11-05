package com.fdi17.common.exception;


import com.fdi17.api.common.enums.BaseErrorCode;

/**
 * 验证码错误异常类
 * 
 * @author lujingbo
 */
public class RongSystemException extends BaseException {
	private static final long serialVersionUID = 1L;

	public RongSystemException() {
		super("com.fdi17.system.error");
	}


	public RongSystemException(int code, String msg) {
		super(String.valueOf(code), msg);
	}
	public RongSystemException(String code, String msg) {
		super(code, msg);
	}

	public RongSystemException(BaseErrorCode errors) {
		super(errors.getCode(), errors.message());
	}

	public RongSystemException(BaseErrorCode errors, Throwable cause) {
		super(errors.getCode(), cause.getMessage());
	}

	public RongSystemException(String msg, BaseErrorCode errors) {
		super(msg, errors.message());
	}
}
