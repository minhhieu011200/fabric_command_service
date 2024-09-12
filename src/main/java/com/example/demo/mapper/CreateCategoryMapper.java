package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.example.demo.domain.entities.Category;
import com.example.demo.dto.CreateCategoryDTO;

@Mapper
public interface CreateCategoryMapper extends BaseMapper<CreateCategoryDTO, Category> {
	CreateCategoryMapper INSTANCE = Mappers.getMapper( CreateCategoryMapper.class );
}
