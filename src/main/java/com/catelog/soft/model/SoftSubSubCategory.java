package com.catelog.soft.model;

import java.time.LocalDate;
import java.util.Date;

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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Soft_Sub_Sub_Category")
public class SoftSubSubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Sub_Sub_Category_Name")
	@Size(min = 3,max = 20,message = "subSubCategoryName  range should be 3 to 20")
	private String subSubCategoryName;
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	@Column(name = "Desription")
	@Size(min = 3,max = 30,message = "description  range should be 3 to 30")
	private String description;
	@Column(name = "User_Name")
	@Size(min = 3,max = 20,message = "userName  range should be 3 to 20")
	private String userName;

	@ManyToOne(targetEntity =SoftCategoryInfo.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(
		     name = "category_info_id",
		     nullable = false,
		     referencedColumnName = "id", 
		     foreignKey =@ForeignKey(foreignKeyDefinition = "category_info_id")
		   )
	private SoftCategoryInfo categoryInfo;
	
	@ManyToOne(targetEntity =SoftSubCategoryInfo.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(
		     name = "sub_category_info_id",
		     nullable = false,
		     referencedColumnName = "id", 
		     foreignKey =@ForeignKey(foreignKeyDefinition = "sub_category_info_id")
		   )
	private SoftSubCategoryInfo softCategoryInfo;

	public SoftCategoryInfo getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(SoftCategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public SoftSubCategoryInfo getSubSoftCategoryInfo() {
		return softCategoryInfo;
	}

	public void setSubSoftCategoryInfo(SoftSubCategoryInfo softCategoryInfo) {
		this.softCategoryInfo = softCategoryInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubSubCategoryName() {
		return subSubCategoryName;
	}

	public void setSubSubCategoryName(String subSubCategoryName) {
		this.subSubCategoryName = subSubCategoryName;
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
