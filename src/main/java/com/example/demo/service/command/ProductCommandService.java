package com.example.demo.service.command;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Product;
import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Outbox;
import com.example.demo.dto.CreateProductDTO;
import com.example.demo.dto.UpdateProductDTO;
import com.example.demo.dto.reponse.CreateProductResponseDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.CreateProductMapper;
import com.example.demo.mapper.OutBoxCreateProductMapper;
import com.example.demo.mapper.OutBoxMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OutBoxRepository;
import com.example.demo.util.Constant;
import com.example.demo.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductCommandService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OutBoxRepository outBoxRepository;
	
//	@Autowired
	private CreateProductMapper createProductMapper;

//	@Autowired
//	private OutBoxCreateProductMapper outBoxCreateProductMapper;
	
	@Autowired
	private OutBoxMapper<Product> outBoxMapper;

	@Transactional
	public Product createProduct(CreateProductDTO data) {
		existsByName(data.getName());
		Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow(() -> new AppException(ErrorCode.ID_EXIST));
		Product product = new Product();
		product.setName(data.getName());
		product.setNote(data.getNote());
		product.setCount(data.getCount());
		product.setCategory(category);

		if (data.getIsDelete() != null) {
			product.setIsDelete(data.getIsDelete());
		}

		if (data.getIsDelete() != null && data.getIsDelete() == true) {
			product.setDateDelete();
		}
		product = productRepository.save(product);
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_ADD_PRODUCT, product);

		outBoxRepository.save(outbox);

		return product;
	}

	@Transactional
	public Product updateProduct(Integer id, UpdateProductDTO data) {
		if (productRepository.existsByNameAndIdNot(data.getName(), id)) {
			throw new AppException(ErrorCode.NAME_EXIST);
		}

		Product productUpdate = findProductId(id);
		if (!StringUtil.isNullOrWhiteSpace(data.getName())) {
			productUpdate.setName(data.getName());
		}

		if (!StringUtil.isNullOrWhiteSpace(data.getNote())) {
			productUpdate.setNote(data.getNote());
		}

		if (data.getCount() != null) {
			productUpdate.setCount(data.getCount());
		}
		if (data.getIsDelete() != null) {
			productUpdate.setIsDelete(data.getIsDelete());
		}

		if (data.getIsDelete() != null && data.getIsDelete() == true) {
			productUpdate.setDateDelete();
		}
		productUpdate = productRepository.save(productUpdate);
		
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_UPDATE_PRODUCT, productUpdate);
		outBoxRepository.save(outbox);

		return productUpdate;
	}

	@Transactional
	public void deleteProduct(Integer id) {
		Product productId = findProductId(id);
		productRepository.deleteById(id);
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_DELETE_PRODUCT, productId);
		outBoxRepository.save(outbox);
	}

	private Product findProductId(Integer id) {
		return productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ID_EXIST));
	}

	private boolean existsByName(String data) {
		if (productRepository.existsByName(data)) {
			throw new AppException(ErrorCode.NAME_EXIST);
		}
		return false;
	}
}
