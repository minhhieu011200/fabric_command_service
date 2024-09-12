package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Product;
import com.example.demo.dto.SearchProductDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryCustom implements IProductRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
	private JpaSpecificationExecutor<Product> executor;
	
	@Override
	public Page<Product> findAllSearchProduct(Integer pageNo, Integer pageSize, SearchProductDTO searchProductDTO) {
		// TODO Auto-generated method stub
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		/*
		 * Specification<Product> specification = (root, query, criteriaBuilder) -> {
		 * 
		 * }
		 */

//		return executor.findAll();
		return null;
	}


}
