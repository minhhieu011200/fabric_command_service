package com.example.demo.repository;

import org.springframework.data.domain.Page;
import com.example.demo.domain.entities.Category;
import com.example.demo.dto.SearchCategoryDTO;


public interface ICategoryRepositoryCustom {
	
	Page<Category> findAllSearchCategory(Integer pageNo, Integer pageSize, SearchCategoryDTO searchCategoryDTO);
}
