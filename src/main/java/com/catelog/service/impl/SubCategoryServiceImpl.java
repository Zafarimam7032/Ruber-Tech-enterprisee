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
import com.catelog.service.SubCatelogService;
import com.catelog.soft.model.SoftCategory;
import com.catelog.soft.model.SoftCategoryInfo;
import com.catelog.soft.model.SoftSubCategory;
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.repository.SoftCategoryRepository;
import com.catelog.soft.repository.SoftSubCategoryRepository;
import com.catelog.soft.repository.SoftSubSubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCatelogService {

	private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository catelogRepository;

	@Autowired
	private SubSubCategoryRepository subSubCategoryRepository;

	@Autowired
	private SoftCategoryRepository softCategoryRepository;

	@Autowired
	private SoftSubCategoryRepository softSubCategoryRepository;

	@Autowired
	private SoftSubSubCategoryRepository softSubSubCategoryRepository;

	@Override
	public List<SubCategory> getSubCategories() {
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		if (subCategories.size() > 0) {
			return subCategories;
		}
		return null;
	}

	@Override
	public SubCategory getSubCategory(String categoryName, String subCategoryName) {
		Category category = catelogRepository.findByCategoryName(categoryName);
		if (Objects.nonNull(category) && Objects.nonNull(subCategoryName)) {
			SubCategory subCategory = category.getSubCategory().stream()
					.filter(subcategory -> subcategory.getSubCategoryName().equalsIgnoreCase(subCategoryName))
					.findFirst().orElse(null);
			if (Objects.nonNull(subCategory)) {
				return subCategory;
			}
		}
		return null;
	}

	@Override
	public boolean updateSubCategoryName(String categoryName, String oldSubCategoryName, String subcategoryName) {
		boolean check = false;
		Category category = catelogRepository.findByCategoryName(categoryName);
		if (Objects.nonNull(oldSubCategoryName) && Objects.nonNull(category)) {
			SubCategory subCategory = category.getSubCategory().stream()
					.filter(subcateg -> subcateg.getSubCategoryName().equalsIgnoreCase(oldSubCategoryName)).findFirst()
					.orElse(null);
			SubCategory addSubCategoryinfo = category.getSubCategory().stream()
					.filter(subcateg -> subcateg.getSubCategoryName().equalsIgnoreCase(subcategoryName)).findFirst()
					.orElse(null);
			if ((Objects.nonNull(subCategory)) && !(Objects.nonNull(addSubCategoryinfo))
					&& Objects.nonNull(subcategoryName)) {
				subCategory.setSubCategory(subcategoryName);
				SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
				if (Objects.nonNull(savedSubCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public boolean deletesubCategory(String categoryName, String subCategoryName) {
		logger.info("inside deletesubCategory=== method");
		boolean check = false;
		Category category = catelogRepository.findByCategoryName(categoryName);
		if (Objects.nonNull(category)) {
			SubCategory subCategory = category.getSubCategory().stream()
					.filter(subcat -> subcat.getSubCategoryName().equalsIgnoreCase(subCategoryName)).findFirst()
					.orElse(null);
			List<SoftSubSubCategory> softSubSubCategories = new ArrayList<SoftSubSubCategory>();
			SoftCategoryInfo softCategoryInfo = new SoftCategoryInfo();
			SoftSubCategory softSubCategory = new SoftSubCategory();
			if (Objects.nonNull(subCategory)) {
				softSubCategory.setId(subCategory.getId());
				softSubCategory.setSubCategory(subCategory.getSubCategoryName());
				softSubCategory.setDate(subCategory.getDate());
				softSubCategory.setDescription(subCategory.getDescription());
				softSubCategory.setUserName(subCategory.getUserName());
				List<SubSubCategory> subSubCategories = subCategory.getSubSubCategories();
				if (subSubCategories.size() > 0) {
					for (SubSubCategory subSubCategory : subSubCategories) {
						SoftSubSubCategory softSubSubCategory = new SoftSubSubCategory();
						softSubSubCategory.setId(subSubCategory.getId());
						softSubSubCategory.setDate(subSubCategory.getDate());
						softSubSubCategory.setDescription(subSubCategory.getDescription());
						softSubSubCategory.setSubSubCategoryName(subSubCategory.getSubSubCategoryName());
						softSubSubCategory.setUserName(subSubCategory.getUserName());
						subSubCategoryRepository.deleteById(subSubCategory.getId());
						subSubCategoryRepository.deleteSubSubCategory(subSubCategory.getId());
						softSubSubCategories.add(softSubSubCategory);
					}
				}
				softSubCategory.setSubSubCategories(softSubSubCategories);
				softCategoryInfo.setCategoryName(categoryName);
				softSubCategory.setCategoryInfo(softCategoryInfo);
				SoftSubCategory savedSoftSubCategory = softSubCategoryRepository.save(softSubCategory);
				subCategoryRepository.deleteById(subCategory.getId());
				subCategoryRepository.deleteSubCategory(subCategory.getId());
				if (Objects.nonNull(savedSoftSubCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public boolean addSubCategory(String categoryName, SubCategory subCategory) {
		boolean check = false;
		if ((Objects.nonNull(categoryName)) && (Objects.nonNull(subCategory))) {
			Category category = catelogRepository.findByCategoryName(categoryName);
			SubCategory subcategoryInfo = category.getSubCategory().stream()
					.filter(subcat -> subcat.getSubCategoryName().equalsIgnoreCase(subCategory.getSubCategoryName()))
					.findFirst().orElse(null);
			SoftSubCategory softSubCategory = softSubCategoryRepository
					.findBySubCategory(subCategory.getSubCategoryName());
			if (Objects.nonNull(category) && !(Objects.nonNull(subcategoryInfo))
					&& !(Objects.nonNull(softSubCategory))) {
				category.getSubCategory().add(subCategory);
				Category savedCategory = catelogRepository.save(category);
				if (Objects.nonNull(savedCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

}
