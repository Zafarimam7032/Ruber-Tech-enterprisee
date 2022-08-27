package com.catelog.model;

import java.time.LocalDate;
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Sub_Sub_Category")
public class SubSubCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonProperty(value = "subSubCategoryName")
	@Size(min = 3 ,max = 20, message = "subSubCategoryName range will be 3 to 20")
	private String subSubCategoryName;
	
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	
	@Column(name = "Desription")
	@Size(min = 3 ,max = 30, message = "subSubCategoryName range will be 3 to 30")
	private String description;
	
	@Column(name = "User_Name")
	@Size(min = 3 ,max = 20, message = "userName range will be 3 to 20")
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
