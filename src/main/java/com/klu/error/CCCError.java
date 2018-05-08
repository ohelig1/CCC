package com.klu.error;

public class CCCError extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;
	
	public CCCError(int code, String message, Exception e) {
		this.errorMessage = message;
		this.errorCode = code;
	}
	
	public CCCError(int code, String message) {
		this.errorMessage = message;
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
