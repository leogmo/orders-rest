package com.cjl.exceptions;

public class InvalidUserException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidUserException() {
		super("Invalid User Exception");
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
