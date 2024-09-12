package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Category;
import com.example.demo.dto.SearchCategoryDTO;
import com.example.demo.util.StringUtil;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

@Repository
public class CategoryRepositoryCustom implements ICategoryRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	private JpaSpecificationExecutor<Category> executor;
	
//    @PostConstruct
//    public void init() {
//        executor = new SimpleJpaRepository<>(Category.class, entityManager);
//    }
	
	@Override
	public Page<Category> findAllSearchCategory(Integer pageNo, Integer pageSize, SearchCategoryDTO searchCategoryDTO) {
		  Pageable pageable = PageRequest.of(pageNo, pageSize);
		  
//	     Specification<Category> specification = (root, query, criteriaBuilder) -> {
//	            List<Predicate> predicates = new ArrayList<>();
//
//	            if (StringUtil.isNullOrWhiteSpace(searchCategoryDTO.getName())) {
//	                predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchCategoryDTO.getName() + "%"));
//	            }
//				/*
//				 * if (category != null && !category.isEmpty()) {
//				 * predicates.add(criteriaBuilder.equal(root.get("category"), category)); } if
//				 * (minPrice != null) {
//				 * predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
//				 * minPrice)); } if (maxPrice != null) {
//				 * predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
//				 * maxPrice)); }
//				 */
//
//	            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//	        };
//	        
		return null;
	}

}
