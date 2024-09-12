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
import com.example.demo.dto.CreateProductDTO;
import com.example.demo.dto.SearchProductDTO;
import com.example.demo.dto.UpdateProductDTO;
import com.example.demo.dto.reponse.CreateProductResponseDTO;
import com.example.demo.dto.reponse.Pagination;
import com.example.demo.dto.reponse.ResponseData;
import com.example.demo.service.command.ProductCommandService;
import com.example.demo.service.query.ProductQueryService;

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
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductCommandService productCommandService;

	@Autowired
	ProductQueryService productQueryService;

	@Operation(summary = "get product", description = "get list product")
	@GetMapping
	public ResponseData<Pagination<List<Product>>> getProduct(  
			@ModelAttribute SearchProductDTO searchProductDTO,
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id:desc") String sortBy
          ) {
		return new ResponseData<Pagination<List<Product>>>(productQueryService.getProductPagination(pageNo,pageSize,sortBy,searchProductDTO));
	}

	@Operation(summary = "get detail", description = "get detail product")
	@GetMapping("{id}")
	public ResponseData<Product> getDetailProduct(@PathVariable Integer id) {
		return new ResponseData<Product>(productQueryService.getDetailProduct(id));
	}

	@Operation(summary = "create product", description = "create new product")
	@PostMapping
	public ResponseData<Product> postProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
		return new ResponseData<Product>(productCommandService.createProduct(createProductDTO));
	}

	@Operation(summary = "update product", description = "update new product")
	@PutMapping("{id}")
	public ResponseData<Product> putProduct(@PathVariable Integer id, @RequestBody UpdateProductDTO updateProductDTO) {
		return new ResponseData<>(productCommandService.updateProduct(id, updateProductDTO));
	}

	@DeleteMapping("{id}")
	public ResponseData<?> deleteProduct(@PathVariable Integer id) {
		productCommandService.deleteProduct(id);
		return new ResponseData<>();
	}

}
