package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.domain.entities.Product;


@Mapper
public interface GetProductMapper extends BaseMapper<Product, Product>{
	GetProductMapper INSTANCE = Mappers.getMapper( GetProductMapper.class );
	
}