package com.catelog.service;

import java.util.List;

import com.catelog.model.SubCategory;

public interface SubCatelogService {

	public List<SubCategory> getSubCategories();

	public SubCategory getSubCategory(String categoryName,String subCategoryName);

	public boolean updateSubCategoryName(String categoryName,String oldSubCategoryName,String subcategoryName);

	public boolean deletesubCategory(String categoryName,String subCategoryName);

	public boolean addSubCategory(String categoryName, SubCategory subCategory);
}
