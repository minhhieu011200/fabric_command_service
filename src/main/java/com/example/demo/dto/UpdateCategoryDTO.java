package com.example.demo.dto;


import com.example.demo.exception.ErrorCode;

import jakarta.validation.constraints.NotEmpty;

public class UpdateCategoryDTO {
	
	@NotEmpty(message = "NAME_NOTNULL")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
