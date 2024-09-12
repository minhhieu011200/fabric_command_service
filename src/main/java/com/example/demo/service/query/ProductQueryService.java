package com.example.demo.service.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.dto.SearchProductDTO;
import com.example.demo.dto.reponse.Pagination;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.GetPaginationMapper;
import com.example.demo.mapper.GetProductMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.StringUtil;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductQueryService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private GetPaginationMapper<Product> paginationMapper;

	private GetProductMapper productMapper;

	public Pagination<List<Product>> getProductPagination(int pageNo, int pageSize, String sortBy,
			SearchProductDTO searchProductDTO) {
		String[] sortBySplit = null;
		if (!StringUtil.isNullOrWhiteSpace(sortBy)) {
			sortBySplit = sortBy.split(":");
		}
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.fromString(sortBySplit[1]),
				sortBySplit[0]);

		Specification<Product> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (searchProductDTO.getId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("id"), searchProductDTO.getId()));
			}
			if (!StringUtil.isNullOrWhiteSpace(searchProductDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(root.get("name"), String.format("%%%s%%", searchProductDTO.getName())));
			}

			if (!StringUtil.isNullOrWhiteSpace(searchProductDTO.getNote())) {
				predicates.add(
						criteriaBuilder.like(root.get("note"), String.format("%%%s%%", searchProductDTO.getNote())));
			}

			if (searchProductDTO.getFromCreateDate() != null && searchProductDTO.getToCreateDate() != null) {
				predicates.add(criteriaBuilder.between(root.get("createDate"), searchProductDTO.getFromCreateDate(),
						searchProductDTO.getToCreateDate()));
			}

			if (searchProductDTO.getCategoryId() != null) {
				List<Integer> listCategoryId = Arrays.stream(searchProductDTO.getCategoryId().split(","))
						.map(Integer::parseInt).collect(Collectors.toList());

				predicates.add(root.get("category").get("id").in(listCategoryId));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};

		Page<Product> productPage = productRepository.findAll(specification, pageable);

		return paginationMapper.mapperPagination(productPage, pageSize);
	}

	public Product getDetailProduct(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ID_EXIST));
		return product;
	}

}
