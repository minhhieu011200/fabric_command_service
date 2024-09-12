package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.domain.entities.Category;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.dto.UpdateCategoryDTO;

@Mapper
public interface UpdateCategoryMapper extends BaseMapper<UpdateCategoryDTO, Category>{
	UpdateCategoryMapper INSTANCE = Mappers.getMapper( UpdateCategoryMapper.class );
	
}
