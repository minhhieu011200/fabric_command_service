package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	@EntityGraph(attributePaths = { "category" })
	boolean existsByName(String name);

	@Override
	@EntityGraph(attributePaths = { "category" })
	boolean existsById(Integer id);

	@Override
	@EntityGraph(attributePaths = { "category" })
//	@Query("SELECT p FROM Product p JOIN FETCH p.category")
	Page<Product> findAll(Pageable pageable);

	@EntityGraph(attributePaths = { "category" })
	@Override
//	@Query("SELECT p FROM Product p JOIN FETCH p.category where p.id=:id")
	Optional<Product> findById(Integer id);

	@EntityGraph(attributePaths = { "category" })
//	 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.name = :name AND p.id <> :id")
	boolean existsByNameAndIdNot(String name, Integer id);

	@Override
	@EntityGraph(attributePaths = { "category" })
	Page<Product> findAll(Specification<Product> spec, Pageable pageable);

}
