package com.catelog.soft.service.impl;

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
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.repository.SoftCategoryInformationRepository;
import com.catelog.soft.repository.SoftSubCategoryInformationRepository;
import com.catelog.soft.repository.SoftSubSubCategoryRepository;
import com.catelog.soft.service.SoftSubSubCategoryService;

@Service
public class SoftSubSubCategoryServiceImpl implements SoftSubSubCategoryService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SoftSubSubCategoryRepository softSubSubCategoryRepository;

	@Autowired
	private SubSubCategoryRepository subSubCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private SoftCategoryInformationRepository softCategoryInformationRepository;

	@Autowired
	private SoftSubCategoryInformationRepository softSubCategoryInformationRepository;

	@Override
	public boolean retriveSubSubCategory(String categoryName, String subCategoryName, String subSubCategoryName) {
		boolean check = false;
		List<SoftSubSubCategory> allSoftSubSubCategories = softSubSubCategoryRepository.findAll();
		SoftSubSubCategory softSubSubCategory = allSoftSubSubCategories.stream()
				.filter(softSubSubCategry -> softSubSubCategry.getSubSoftCategoryInfo().getSoftSubCategoryName()
						.equalsIgnoreCase(subCategoryName))
				.filter(softsubsub -> softsubsub.getCategoryInfo().getCategoryName().equalsIgnoreCase(categoryName))
				.findFirst().orElse(null);
		if (Objects.nonNull(softSubSubCategory)) {
			SubSubCategory subSubCategory = new SubSubCategory();
			subSubCategory.setId(softSubSubCategory.getId());
			subSubCategory.setDate(softSubSubCategory.getDate());
			subSubCategory.setUserName(softSubSubCategory.getUserName());
			subSubCategory.setSubSubCategoryName(softSubSubCategory.getSubSubCategoryName());
			subSubCategory.setDescription(softSubSubCategory.getDescription());
			Category category = categoryRepository.findByCategoryName(categoryName);
			SubCategory subCategory = category.getSubCategory().stream()
					.filter(subcate -> subcate.getSubCategoryName().equalsIgnoreCase(subCategoryName)).findFirst()
					.orElse(null);
			subCategory.getSubSubCategories().add(subSubCategory);
			SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
			if (Objects.nonNull(savedSubCategory)) {
				softSubSubCategoryRepository.updateSoftCategoryId(softSubSubCategory.getId());
				softSubSubCategoryRepository.updateSoftSubCategoryId(softSubSubCategory.getId());
				softSubSubCategoryRepository.deleteById(softSubSubCategory.getId());
				softSubCategoryInformationRepository.deleteById(softSubSubCategory.getSubSoftCategoryInfo().getId());
				softCategoryInformationRepository.deleteById(softSubSubCategory.getCategoryInfo().getId());
				check = true;
			}
		}
		return check;
	}

}
