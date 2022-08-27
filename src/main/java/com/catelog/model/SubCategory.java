package com.catelog.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "sub_category")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "subCategoryName")
	@Size(min = 3,max = 15, message = "subCategoryName should not be less than 3 or grater then 20")
	private String subCategoryName;
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	@Column(name = "Desription")
	@Size(min = 3,max = 30, message = "description should not be less than 3 or grater then 30")
	private String description;
	@Column(name = "User_Name")
	@Size(min = 3,max = 20, message = "userName should not be less than 3 or grater then 20")
	private String userName;
	@OneToMany(targetEntity = SubSubCategory.class,cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<SubSubCategory> subSubCategories;
	
	public List<SubSubCategory> getSubSubCategories() {
		return subSubCategories;
	}
	public void setSubSubCategories(List<SubSubCategory> subSubCategories) {
		this.subSubCategories = subSubCategories;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategory(String subCategory) {
		this.subCategoryName = subCategory;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
