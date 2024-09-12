package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {
	@NotNull
	String name;
	Long count;
	
	String note;
	Boolean isDelete;
	
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@NotNull(message = "CATEGORY_NOTNULL")
	Integer categoryId;
	
	public String getName() {
		return name;
	}
	public Long getCount() {
		return count;
	}
	public String getNote() {
		return note;
	}
	public Integer getCategoryId() {
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
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
