package com.catelog.soft.model;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Soft_Category")
public class SoftCategory {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "Category_Name")
	@Size(min = 3,max = 10,message = "category Name range should be 3 to 10")
	private String categoryName;
	@Column(name = "Description")
	@Size(min = 3,max = 30,message = "description  range should be 3 to 30")
	private String description;
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	@Column(name = "User_Name")
	@Size(min = 3,max = 20,message = "userName  range should be 3 to 30")
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
