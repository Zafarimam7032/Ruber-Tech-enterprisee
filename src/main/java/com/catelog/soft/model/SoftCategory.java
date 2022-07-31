package com.catelog.soft.model;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Soft_Category")
public class SoftCategory {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "Category_Name")
//	@Min(value = 4, message = "category name should not be lesser then 4 charector")
//	@Max(value = 10, message = "category Name should not be greater then 10 charector")
	private String categoryName;
	@Column(name = "Description")
	@NotBlank
	private String description;
	@Column(name = "Date")
	private Date date;
	@Column(name = "User_Name")
	private String userName;
	@OneToMany(targetEntity = SoftSubCategoryBackup.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SoftSubCategoryBackup> softSubCategoryBackup;

	public List<SoftSubCategoryBackup> getSoftSubCategoryBackup() {
		return softSubCategoryBackup;
	}

	public void setSoftSubCategoryBackup(List<SoftSubCategoryBackup> category) {
		this.softSubCategoryBackup = category;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
