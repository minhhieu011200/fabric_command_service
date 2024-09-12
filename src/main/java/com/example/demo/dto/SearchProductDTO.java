package com.example.demo.dto;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductDTO {
	Long id;
	String name;
	String categoryId;

	String note;

	Date fromCreateDate;
	Date toCreateDate;
	Date fromeWarehouse;
	Date toWarehouse;
}
