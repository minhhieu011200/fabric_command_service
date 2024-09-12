package com.example.demo.dto;

import com.example.demo.exception.ErrorCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCategoryDTO {
	
	@NotNull
//	@NotBlank(message = "NAME_NOTNULL")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
