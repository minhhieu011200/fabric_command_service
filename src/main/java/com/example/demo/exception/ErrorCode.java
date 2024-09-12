package com.example.demo.exception;

public enum ErrorCode {
	INVALID_CODE(-1,"Invalid error code"),
	UNCATEGORIZED(9999,"Uncategorized error"),
	NAME_NOTNULL(102,"Name is required"),
	CATEGORY_NOTNULL(103,"Category is required"),
	NAME_EXIST(104,"Name is exist"),
	ID_EXIST(105,"ID is exist"),
	;
	
	int errorCode;
	String message;
	
	private ErrorCode(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}
}
