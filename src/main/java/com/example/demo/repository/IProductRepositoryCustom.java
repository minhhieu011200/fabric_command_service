package com.example.demo.repository;

import org.springframework.data.domain.Page;
import com.example.demo.domain.entities.Product;
import com.example.demo.dto.SearchProductDTO;

public interface IProductRepositoryCustom {

	Page<Product> findAllSearchProduct(Integer pageNo, Integer pageSize, SearchProductDTO searchProductDTO);
}
