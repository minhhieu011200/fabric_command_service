package com.example.demo.dto.reponse;

import java.io.Serializable;

public class Pagination<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4222642523352379645L;
	/**
	 * 
	 */
	int pageSize;
	int totalPage;
	long totalElements;
	
	T data;
	
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public T getData() {
		return data;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setData(T data) {
		this.data = data;
	}

}
