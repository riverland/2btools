package org.river.rbtools.utils.exception;


public class FunctionException extends Exception {
	private static final long serialVersionUID = -2076099194144721109L;
	private String message;

	public FunctionException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
