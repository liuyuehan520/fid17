package com.fdi17.common.exception;

public class UserPasswordNotMatchException extends UserException{
	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
		super("user.password.not.match", null);
	}
}
