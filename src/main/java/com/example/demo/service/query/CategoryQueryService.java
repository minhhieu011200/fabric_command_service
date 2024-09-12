package com.example.demo.service.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.dto.SearchCategoryDTO;
import com.example.demo.dto.reponse.Pagination;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.GetPaginationMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.util.StringUtil;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryQueryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private GetPaginationMapper<Category> paginationMapper;

	@Transactional(readOnly = true)
	public Pagination<List<Category>> getProductPagination(Integer pageNo, Integer pageSize, String sortBy,
			SearchCategoryDTO searchCategoryDTO) {

		String[] sortBySplit = null;
		if (!StringUtil.isNullOrWhiteSpace(sortBy)) {
			sortBySplit = sortBy.split(":");
		}
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.fromString(sortBySplit[1]),
				sortBySplit[0]);


		Specification<Category> specification = (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			if (searchCategoryDTO.getId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("id"), searchCategoryDTO.getId()));
			}
			if (!StringUtil.isNullOrWhiteSpace(searchCategoryDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(root.get("name"), String.format("%%%s%%", searchCategoryDTO.getName())));
			}
			if (searchCategoryDTO.getFromCreateDate() != null && searchCategoryDTO.getToCreateDate() != null) {
				predicates.add(criteriaBuilder.between(root.get("createDate"), searchCategoryDTO.getFromCreateDate(),
						searchCategoryDTO.getToCreateDate()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		Page<Category> categoryPage = categoryRepository.findAll(specification, pageable);

		return paginationMapper.mapperPagination(categoryPage, pageSize);
	}

	public Category getDetailCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ID_EXIST));
//		List<Product> products = category.getProducts();

//		= strings.stream().collect(Collectors.toList());
		return category;
	}
}
