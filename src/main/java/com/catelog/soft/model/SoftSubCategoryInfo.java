package com.catelog.soft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SoftSubCategoryInfo")
public class SoftSubCategoryInfo {
	
	@Id
	@GeneratedValue
	private long id;
	@Size(min = 3,max = 20,message = "categoryName  range should be 3 to 30")
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
