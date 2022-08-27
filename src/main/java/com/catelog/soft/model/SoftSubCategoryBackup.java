package com.catelog.soft.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Soft_sub_category_Backup")
public class SoftSubCategoryBackup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Sub_Category_Name")
	@Size(min = 3,max = 20,message = "subCategory  range should be 3 to 30")
	private String subCategory;
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	@Column(name = "Desription")
	@Size(min = 3,max = 30,message = "description  range should be 3 to 30")
	private String description;
	@Column(name = "User_Name")
	@Size(min = 3,max = 20,message = "userName  range should be 3 to 30")
	private String userName;
	@OneToMany(targetEntity =SoftSubSubCategoryBackup.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SoftSubSubCategoryBackup> softSubSubCategoryBackup;
	
	public List<SoftSubSubCategoryBackup> getSoftSubSubCategoryBackup() {
		return softSubSubCategoryBackup;
	}
	public void setSoftSubSubCategoryBackup(List<SoftSubSubCategoryBackup> subSubCategories) {
		this.softSubSubCategoryBackup = subSubCategories;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
