package com.catelog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catelog.model.Category;
import com.catelog.model.SubCategory;
import com.catelog.model.SubSubCategory;
import com.catelog.repository.CategoryRepository;
import com.catelog.repository.SubCategoryRepository;
import com.catelog.repository.SubSubCategoryRepository;
import com.catelog.service.CatelogService;
import com.catelog.soft.model.SoftCategory;
import com.catelog.soft.model.SoftSubCategory;
import com.catelog.soft.model.SoftSubCategoryBackup;
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.model.SoftSubSubCategoryBackup;
import com.catelog.soft.repository.SoftCategoryRepository;

@Service
public class CatelogServiceImpl implements CatelogService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryRepository catelogRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private SubSubCategoryRepository subSubCategoryRepository;

	@Autowired
	private SoftCategoryRepository softCategoryRepository;

	@Override
	public List<Category> getAllCategory() {
		logger.debug("stating category invokation");
		List<Category> categories = catelogRepository.findAll();
		logger.info(String.valueOf(categories.size()));
		if (Objects.nonNull(categories)) {
			return categories;
		}
		return null;
	}

	@Override
	public Category getCategoryByCategoryName(String categoryName) {
		if (categoryName != null) {
			Category category = catelogRepository.findByCategoryName(categoryName);
			if (Objects.nonNull(category)) {
				return category;
			}
		}
		return null;
	}

	@Override
	public boolean updateCategoryName(String categoryName, String OldCategoryName) {
		boolean check = false;
		if (OldCategoryName != null) {
			Category category = catelogRepository.findByCategoryName(OldCategoryName);
			if (Objects.nonNull(category)) {
				category.setCategoryName(categoryName);
				Category savedCategory = catelogRepository.save(category);
				if (Objects.nonNull(savedCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public boolean deleteCategoryName(String categoryname) {
		boolean check = false;
		if (categoryname != null) {
			Category category = catelogRepository.findByCategoryName(categoryname);
			if (Objects.nonNull(category)) {
				SoftCategory softCategory = new SoftCategory();
				softCategory.setUserName(category.getUserName());
				softCategory.setDate(category.getDate());
				softCategory.setDescription(category.getDescription());
				softCategory.setId(category.getId());
				softCategory.setCategoryName(category.getCategoryName());
				List<SubCategory> subCategories = category.getSubCategory();
				List<SoftSubCategoryBackup> softSubCategoriesBackups = new ArrayList<>();
				List<SoftSubSubCategoryBackup> softSubSubCategoriesBackups = new ArrayList<>();
				if (subCategories.size() > 0) {
					for (SubCategory subCategory : subCategories) {
						SoftSubCategoryBackup softSubCategory = new SoftSubCategoryBackup();
						softSubCategory.setId(subCategory.getId());
						softSubCategory.setDate(subCategory.getDate());
						softSubCategory.setDescription(subCategory.getDescription());
						softSubCategory.setSubCategory(subCategory.getSubCategoryName());
						softSubCategory.setUserName(subCategory.getUserName());
						List<SubSubCategory> subSubCategories = subCategory.getSubSubCategories();
						if (subSubCategories.size() > 0) {
							for (SubSubCategory subSubCategory : subSubCategories) {
								SoftSubSubCategoryBackup softSubSubCategory = new SoftSubSubCategoryBackup();
								softSubSubCategory.setId(subSubCategory.getId());
								softSubSubCategory.setDate(subSubCategory.getDate());
								softSubSubCategory.setDescription(subSubCategory.getDescription());
								softSubSubCategory.setSubSubCategoryName(subSubCategory.getSubSubCategoryName());
								softSubSubCategory.setUserName(subSubCategory.getUserName());
								softSubSubCategoriesBackups.add(softSubSubCategory);
								subSubCategoryRepository.deleteById(subSubCategory.getId());
								subSubCategoryRepository.deleteSubSubCategory(subSubCategory.getId());
							}
						}
						softSubCategory.setSoftSubSubCategoryBackup(softSubSubCategoriesBackups);
						softSubCategoriesBackups.add(softSubCategory);
						subCategoryRepository.deleteById(subCategory.getId());
						subCategoryRepository.deleteSubCategory(subCategory.getId());
					}
				}
				softCategory.setSoftSubCategoryBackup(softSubCategoriesBackups);
				softCategoryRepository.save(softCategory);
				catelogRepository.deleteById(category.getId());
				catelogRepository.deleteCategory(category.getId());
				check = true;
			}
		}
		return check;
	}

	@Override
	public boolean addCategory(Category category) {
		boolean check = false;
		Category dbCategoryName = catelogRepository.findByCategoryName(category.getCategoryName());
		SoftCategory softCategory = softCategoryRepository.findByCategoryName(category.getCategoryName());
		if (!(Objects.nonNull(dbCategoryName)) && !(Objects.nonNull(softCategory))) {
			Category savedCategory = catelogRepository.save(category);
			if (Objects.nonNull(savedCategory)) {
				check = true;
			}
		}
		return check;
	}

}
