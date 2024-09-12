package com.example.demo.dto.reponse;

import com.example.demo.domain.base.Entities;
import com.example.demo.domain.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponseDTO  extends Entities {
	private String name;
	private Long count;
	private String note;
	private Category category;
}
