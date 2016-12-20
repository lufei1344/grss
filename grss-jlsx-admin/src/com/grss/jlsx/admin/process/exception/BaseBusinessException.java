package com.grss.jlsx.admin.process.exception;

public class BaseBusinessException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BaseBusinessException() {
		super();
	}
	public BaseBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseBusinessException(String message) {
		super(message);
	}

	public BaseBusinessException(Throwable cause) {
		super(cause);
	}
	
}
