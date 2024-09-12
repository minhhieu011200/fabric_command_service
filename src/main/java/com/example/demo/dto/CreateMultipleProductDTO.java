package com.example.demo.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class CreateMultipleProductDTO {

	@NotEmpty
	List<CreateProductDTO> listProduct;
	
	public List<CreateProductDTO> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<CreateProductDTO> listProduct) {
		this.listProduct = listProduct;
	}

}
