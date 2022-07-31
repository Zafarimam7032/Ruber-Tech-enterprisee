package com.catelog.service;

import java.util.List;

import com.catelog.model.Category;

public interface CatelogService {

	public List<Category> getAllCategory();

	public Category getCategoryByCategoryName(String categoryName);

	public boolean updateCategoryName(String CategoryName, String oldCategoryName);

	public boolean deleteCategoryName(String categoryname);
	
	public boolean addCategory(Category category);

}
