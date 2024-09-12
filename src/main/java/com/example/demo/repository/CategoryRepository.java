package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>,JpaSpecificationExecutor<Category> {
	boolean existsByName(String name);
	
//	@EntityGraph(attributePaths = { "products" })
	@Override
//	@Query("SELECT c FROM Category c where c.id=:id")
	Optional<Category> findById(Integer id) ;
}
