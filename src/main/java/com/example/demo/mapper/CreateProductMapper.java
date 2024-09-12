package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.domain.entities.Product;
import com.example.demo.dto.CreateProductDTO;
import com.example.demo.dto.reponse.CreateProductResponseDTO;

@Mapper
public interface CreateProductMapper {
	CreateProductMapper INSTANCE = Mappers.getMapper(CreateProductMapper.class);

//	@Mapping(source = "category.id", target = "categoryId")
	CreateProductResponseDTO toDTO(Product e);

	@Mapping(target = "category.id", ignore = true)
	Product toRemoveCategory(Product e);

}
