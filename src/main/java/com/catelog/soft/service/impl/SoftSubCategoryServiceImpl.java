package com.catelog.soft.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catelog.model.Category;
import com.catelog.model.SubCategory;
import com.catelog.model.SubSubCategory;
import com.catelog.repository.CategoryRepository;
import com.catelog.repository.SubCategoryRepository;
import com.catelog.soft.model.SoftCategoryInfo;
import com.catelog.soft.model.SoftSubCategory;
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.repository.SoftCategoryInformationRepository;
import com.catelog.soft.repository.SoftSubCategoryRepository;
import com.catelog.soft.repository.SoftSubSubCategoryRepository;
import com.catelog.soft.service.SoftSubCategoryService;

@Service
public class SoftSubCategoryServiceImpl implements SoftSubCategoryService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SoftSubCategoryRepository softSubCategoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SoftCategoryInformationRepository softCategoryInformationRepository;
	
	@Autowired
	private SoftSubSubCategoryRepository softSubSubCategoryRepository;
		
	@Override
	public boolean retriveSubCategory(String categoryName, String subCategoryName) {
		boolean check = false;
		List<SoftSubCategory> allSoftSubCategories = softSubCategoryRepository.findAll();
		List<SoftSubCategory> filterSoftSubCategories = allSoftSubCategories.stream()
				.filter(softsubcat -> softsubcat.getSubCategory().equalsIgnoreCase(subCategoryName))
				.collect(Collectors.toList());
		SoftSubCategory softSubCategory = filterSoftSubCategories.stream()
				.filter(softcat -> softcat.getCategoryInfo().getCategoryName().equalsIgnoreCase(categoryName))
				.findFirst().orElse(null);
		List<SubSubCategory> subSubCategories = new ArrayList<>();
		if (Objects.nonNull(softSubCategory)) {
			SubCategory subCategory = new SubCategory();
			List<SoftSubSubCategory> SoftsubSubCategories = softSubCategory.getSubSubCategories();
			if (SoftsubSubCategories.size() > 0) {
				for (SoftSubSubCategory softSubSubCategory : SoftsubSubCategories) {
					SubSubCategory subSubCategory = new SubSubCategory();
					subSubCategory.setId(softSubSubCategory.getId());
					subSubCategory.setUserName(softSubSubCategory.getUserName());
					subSubCategory.setDate(softSubSubCategory.getDate());
					subSubCategory.setSubSubCategoryName(softSubSubCategory.getSubSubCategoryName());
					subSubCategory.setDescription(softSubSubCategory.getDescription());
					subSubCategories.add(subSubCategory);
					softSubSubCategoryRepository.deleteById(softSubSubCategory.getId());
					softSubSubCategoryRepository.deleteSoftSubSubCategory(softSubSubCategory.getId());
				}
			}
			subCategory.setId(softSubCategory.getId());
			subCategory.setUserName(softSubCategory.getUserName());
			subCategory.setDate(softSubCategory.getDate());
			subCategory.setSubCategory(softSubCategory.getSubCategory());
			subCategory.setDescription(softSubCategory.getDescription());
			subCategory.setSubSubCategories(subSubCategories);
			Category category = categoryRepository.findByCategoryName(categoryName);
			softSubCategoryRepository.updateById(softSubCategory.getId());
			softSubCategoryRepository.deleteById(softSubCategory.getId());
			softCategoryInformationRepository.deleteById(softSubCategory.getCategoryInfo().getId());
			if (Objects.nonNull(category)) {
				category.getSubCategory().add(subCategory);
				Category savedCategory = categoryRepository.save(category);
				if (Objects.nonNull(savedCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

}
