package com.example.demo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.base.Entities;
import com.example.demo.service.query.ProductQueryService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
//@RedisHash
@Builder
//@JsonIgnoreProperties(value = { "category" })
public class Product extends Entities implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 397627746711972977L;

	@NotNull
	private String name;

	private Long count;

	@Column(nullable = true)
	private Boolean isDelete;

	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private String dateDelete;

	private String note;

	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
	@JsonManagedReference
	private Category category;

	public String getName() {
		return name;
	}

	public Long getCount() {
		return count;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public String getNote() {
		return note;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public String getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		this.dateDelete = dateFormat.format(date);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", count=" + count + ", isDelete=" + isDelete + ", dateDelete=" + dateDelete
				+ ", note=" + note + ", category=" + category.getId() + "]";
	}
	

}
