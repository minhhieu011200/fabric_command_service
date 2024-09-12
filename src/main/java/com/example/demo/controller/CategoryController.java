package com.example.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.dto.GetCategoryDTO;
import com.example.demo.dto.SearchCategoryDTO;
import com.example.demo.dto.UpdateCategoryDTO;
import com.example.demo.dto.reponse.Pagination;
import com.example.demo.dto.reponse.ResponseData;
import com.example.demo.service.command.CategoryCommandService;
import com.example.demo.service.query.CategoryQueryService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryCommandService categoryCommandService;
	
	@Autowired
	CategoryQueryService categoryQueryService;
	
	@Operation(summary = "get category", description = "get list category")
	@GetMapping
	public ResponseData<Pagination<List<Category>>> getCategory(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id:desc") String sortBy,
            @ModelAttribute SearchCategoryDTO searchCategoryDTO
            ) {
		return new ResponseData<Pagination<List<Category>>>(categoryQueryService.getProductPagination(pageNo,pageSize,sortBy,searchCategoryDTO));
	}
	
	
	@Operation(summary = "get detail", description = "get detail product")
	@GetMapping("{id}")
	public ResponseData<Category> getDetailProduct(@PathVariable Integer id) {
		return new ResponseData<Category>(categoryQueryService.getDetailCategory(id));
	}
	
	
	@Operation(summary = "create category", description = "create new category")
	@PostMapping
	public ResponseData<Category> postCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO) {
		return new ResponseData<Category>(categoryCommandService.createCategory(createCategoryDTO));
	}
	
	
	@Operation(summary = "update category", description = "update new category")
	@PutMapping("{id}")
	public  ResponseData<Category> putCategory(@PathVariable Integer id,@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
		return new ResponseData<>(categoryCommandService.updateCategory(id,updateCategoryDTO));
	}
	
	@DeleteMapping("{id}")
	public ResponseData<?> deleteCategory(@PathVariable Integer id) {
		categoryCommandService.deleteCategory(id);
		return new ResponseData<>();
	}
	
}
