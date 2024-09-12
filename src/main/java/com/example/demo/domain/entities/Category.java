package com.example.demo.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;


import com.example.demo.domain.base.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
//@RedisHash
public class Category extends Entities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6784724632683491049L;

	/**
	 * 
	 */

	@NotNull
	String name;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	 * 
	 * @JsonBackReference List<Product> products = new ArrayList<Product>();
	 */

	public String getName() {
		return name;
	}

	/*
	 * public List<Product> getProducts() { return products; }
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public void setProducts(List<Product> products) { this.products = products; }
	 */
}
