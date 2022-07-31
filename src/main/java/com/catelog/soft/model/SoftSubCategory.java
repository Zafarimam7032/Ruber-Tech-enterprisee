package com.catelog.soft.model;

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

@Entity
@Table(name = "Soft_sub_category")
public class SoftSubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Sub_Category_Name")
//	@Min(value = 3,message = "sub category should not lesser then 3 charector")
//	@Max(value =9,message = "sub Category should not greater then 9 charector" )
	private String subCategory;
	@Column(name = "Date")
	@NotNull
	private Date date;
	@Column(name = "Desription")
	@NotNull
	private String description;
	@Column(name = "User_Name")
	@NotNull
	private String userName;
	@OneToMany(targetEntity =SoftSubSubCategory.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SoftSubSubCategory> subSubCategories;
	@ManyToOne(targetEntity =SoftCategoryInfo.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(
		     name = "category_info_id",
		     nullable = false,
		     referencedColumnName = "id", 
		     foreignKey =@ForeignKey(foreignKeyDefinition = "category_info_id")
		   )
	private SoftCategoryInfo categoryInfo;
	
	public SoftCategoryInfo getCategoryInfo() {
		return categoryInfo;
	}
	public void setCategoryInfo(SoftCategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}
	public List<SoftSubSubCategory> getSubSubCategories() {
		return subSubCategories;
	}
	public void setSubSubCategories(List<SoftSubSubCategory> subSubCategories) {
		this.subSubCategories = subSubCategories;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
