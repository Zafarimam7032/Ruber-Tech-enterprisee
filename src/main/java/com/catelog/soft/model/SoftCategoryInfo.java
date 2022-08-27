package com.catelog.soft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SoftCategoryInfo")
public class SoftCategoryInfo {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "categoryName")
	@Size(min = 3,max = 10,message = "category Name range should be 3 to 10")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
