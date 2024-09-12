package com.example.demo.dto;

import java.sql.Date;

public class BaseDTO {
	Integer id;
	Integer version;
	Date createDate;
	Date updateDate;
	public Integer getId() {
		return id;
	}
	public Integer getVersion() {
		return version;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
