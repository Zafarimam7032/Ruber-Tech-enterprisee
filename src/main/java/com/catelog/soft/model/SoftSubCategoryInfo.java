package com.catelog.soft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SoftSubCategoryInfo")
public class SoftSubCategoryInfo {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "categoryName")
	private String softSubcategoryName;

	public String getSoftSubCategoryName() {
		return softSubcategoryName;
	}

	public void setSoftSubCategoryName(String softSubCategoryName) {
		this.softSubcategoryName = softSubCategoryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
