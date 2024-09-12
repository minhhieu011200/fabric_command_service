package com.example.demo.mapper;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.demo.dto.reponse.Pagination;

@Component
public class GetPaginationMapper<T>  {
	public Pagination<List<T>> mapperPagination(Page<T> tPage,Integer pageSize){
		Pagination<List<T>> mapperList=new Pagination<List<T>>();
		mapperList.setPageSize(pageSize);
		mapperList.setTotalPage(tPage.getTotalPages());
		mapperList.setData(tPage.getContent());
		mapperList.setTotalElements(tPage.getTotalElements());
		return mapperList;
	}
	
}
