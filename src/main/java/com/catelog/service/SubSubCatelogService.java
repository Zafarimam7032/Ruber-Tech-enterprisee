package com.catelog.service;

import java.util.List;

import com.catelog.model.SubSubCategory;

public interface SubSubCatelogService {
	public List<SubSubCategory> getSubSubCategories();

	public SubSubCategory getSubSubCategory(String categoryName,String subCategory,String subSubCategoryName);

	public boolean updatesubSubCategoryName(String categoryName,String subCategoryName, String subsubCategoryName,
			String oldSubSubCategoryname);

	public boolean deletesubSubCategoryName(String categoryName,String subCategoryName,String subSubCategoryName);

	public boolean addsubSubCategory(String categoryName,String subCategory, SubSubCategory category);

}
