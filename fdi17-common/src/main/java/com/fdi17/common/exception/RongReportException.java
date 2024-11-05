package com.fdi17.common.exception;


import com.fdi17.api.common.enums.BaseErrorCode;

/**
 * 验证码错误异常类
 * 
 * @author lujingbo
 */
public class RongReportException extends BaseException {
	private static final long serialVersionUID = 1L;

	public RongReportException() {
		super("com.fdi17.system.error");
	}


	public RongReportException(int code, String msg) {
		super(String.valueOf(code), msg);
	}
	public RongReportException(String code, String msg) {
		super(code, msg);
	}

	public RongReportException(BaseErrorCode errors) {
		super(errors.getCode(), errors.message());
	}

	public RongReportException(BaseErrorCode errors, Throwable cause) {
		super(errors.getCode(), cause.getMessage());
	}

	public RongReportException(String msg, BaseErrorCode errors) {
		super(msg, errors.message());
	}
}
