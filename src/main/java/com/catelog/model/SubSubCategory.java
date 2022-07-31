package com.catelog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Sub_Sub_Category")
public class SubSubCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonProperty(value = "subSubCategoryName")
//	@Min(value = 3,message = "sub sub Category name should not be lesser then 3 charetor")
//	@Max(value = 9,message = "sub sub category name should not be greater then 9 charetor long ")
	private String subSubCategoryName;
	
	@Column(name = "Date")
	@NotNull
	private Date date;
	
	@Column(name = "Desription")
	@NotNull
	private String description;
	
	@Column(name = "User_Name")
	@NotNull
	private String userName;
	
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
