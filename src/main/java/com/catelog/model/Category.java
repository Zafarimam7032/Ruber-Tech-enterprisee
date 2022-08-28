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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Category_Name")
	@Size(min = 3,max = 10,message = "category Name range should be 3 to 10")
	private String categoryName;
	@Column(name = "Description")
	@Size(min = 3,max = 30,message = "category description range should be 3 to 10")
	private String description;
	@Column(name = "Date")
	@NotEmpty
	private LocalDate date;
	@Column(name = "User_Name")
	@Size(min = 3,max = 10,message = "user Name range should be 3 to 10")
	private String userName;
	@OneToMany(targetEntity = SubCategory.class,cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<SubCategory> subCategories;

	public List<SubCategory> getSubCategory() {
		return subCategories;
	}

	public void setSubCategory(List<SubCategory> category) {
		this.subCategories = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
