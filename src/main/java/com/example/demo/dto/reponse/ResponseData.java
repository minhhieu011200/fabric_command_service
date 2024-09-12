package com.example.demo.dto.reponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
	public ResponseData() {
		super();
	}
	public ResponseData(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	public ResponseData(T data) {
		super();
		this.data = data;
	}
	
	
	private int code=0;
	private String message;
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
