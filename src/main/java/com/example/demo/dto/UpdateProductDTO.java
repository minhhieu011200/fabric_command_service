package com.example.demo.dto;


public class UpdateProductDTO {
	String name;
	Long count;
	String note;
	String categoryId;
	Boolean isDelete;
	
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getName() {
		return name;
	}
	public Long getCount() {
		return count;
	}
	public String getNote() {
		return note;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
