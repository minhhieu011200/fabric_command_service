package com.example.demo.service.command;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Outbox;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.dto.UpdateCategoryDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.CreateCategoryMapper;
import com.example.demo.mapper.OutBoxMapper;
import com.example.demo.mapper.UpdateCategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OutBoxRepository;
import com.example.demo.util.Constant;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryCommandService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OutBoxRepository outBoxRepository;
	private CreateCategoryMapper createCategoryMapper;

	@Autowired
	private OutBoxMapper<Category> outBoxMapper;

	@Transactional
	public Category createCategory(CreateCategoryDTO data) {
		existsByName(data.getName());
		Category category = createCategoryMapper.INSTANCE.toEntity(data);
		category = categoryRepository.save(category);
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_ADD_CATEGORY, category);
		outBoxRepository.save(outbox);

		return category;
	}

	@Transactional
	public Category updateCategory(Integer id, UpdateCategoryDTO data) {
		existsByName(data.getName());
		Category categoryUpdate = findCategoryId(id);
		categoryUpdate.setName(data.getName());
		categoryUpdate = categoryRepository.save(categoryUpdate);
		
		categoryUpdate.setVersion(categoryUpdate.getVersion()+1);
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_UPDATE_CATEGORY, categoryUpdate);
		outBoxRepository.save(outbox);

		return categoryUpdate;
	}

	@Transactional
	public void deleteCategory(Integer id) {
		Category categoryId = findCategoryId(id);
		categoryRepository.deleteById(id);
		Outbox outbox = outBoxMapper.toEntity(Constant.EVENT_DELETE_CATEGORY, categoryId);
		outBoxRepository.save(outbox);
	}

	private Category findCategoryId(Integer id) {
		return categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ID_EXIST));
	}

	private boolean existsByName(String data) {
		if (categoryRepository.existsByName(data)) {
			throw new AppException(ErrorCode.NAME_EXIST);
		}
		return false;
	}
}
