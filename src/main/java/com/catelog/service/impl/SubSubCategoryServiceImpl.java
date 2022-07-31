package com.catelog.service.impl;

import java.util.Arrays;
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
import com.catelog.service.SubSubCatelogService;
import com.catelog.soft.model.SoftCategoryInfo;
import com.catelog.soft.model.SoftSubCategoryInfo;
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.repository.SoftSubSubCategoryRepository;

@Service
public class SubSubCategoryServiceImpl implements SubSubCatelogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubSubCategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubSubCategoryRepository subSubCategoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private SoftSubSubCategoryRepository softSubSubCategoryRepository;

	@Override
	public List<SubSubCategory> getSubSubCategories() {
		List<SubSubCategory> subSubCategories = subSubCategoryRepository.findAll();
		if (Objects.nonNull(subSubCategories)) {
			return subSubCategories;
		}
		return null;
	}

	@Override
	public SubSubCategory getSubSubCategory(String categoryName, String subCategory, String subSubCategoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		SubCategory dbSubCategory = category.getSubCategory().stream()
				.filter(subCat -> subCat.getSubCategoryName().equalsIgnoreCase(subCategory)).findFirst().orElse(null);
		if (Objects.nonNull(dbSubCategory)) {
			SubSubCategory dbSubSubCategory = dbSubCategory.getSubSubCategories().stream().filter(
					subSubCategory -> subSubCategory.getSubSubCategoryName().equalsIgnoreCase(subSubCategoryName))
					.findFirst().orElse(null);
			if (Objects.nonNull(dbSubSubCategory)) {
				return dbSubSubCategory;
			}
		}
		return null;
	}

	@Override
	public boolean updatesubSubCategoryName(String categoryName, String subCategoryName, String oldSubSubCategoryname,
			String subsubCategoryName) {
		boolean check = false;
		Category category = categoryRepository.findByCategoryName(categoryName);
		SubCategory subCategory = category.getSubCategory().stream()
				.filter(subcat -> subcat.getSubCategoryName().equalsIgnoreCase(subCategoryName)).findFirst()
				.orElse(null);
		if (Objects.nonNull(subCategory)) {
			SubSubCategory subSubCategory = subCategory.getSubSubCategories().stream()
					.filter(subsubcat -> subsubcat.getSubSubCategoryName().equalsIgnoreCase(oldSubSubCategoryname))
					.findFirst().orElse(null);
			if ((Objects.nonNull(subSubCategory)) && Objects.nonNull(subsubCategoryName)) {
				subSubCategory.setSubSubCategoryName(subsubCategoryName);
				SubSubCategory savedSubSubCategory = subSubCategoryRepository.save(subSubCategory);
				if (!Objects.nonNull(savedSubSubCategory)) {
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public boolean deletesubSubCategoryName(String categoryName, String subCategoryName, String subSubCategoryName) {
		boolean check = false;
		Category category = categoryRepository.findByCategoryName(categoryName);
		if (Objects.nonNull(category)) {
			SoftCategoryInfo softCategoryInfo=new SoftCategoryInfo();
			softCategoryInfo.setCategoryName(category.getCategoryName());
			SubCategory subCategory = category.getSubCategory().stream()
					.filter(subcat -> subcat.getSubCategoryName().equalsIgnoreCase(subCategoryName)).findFirst()
					.orElse(null);
			if (Objects.nonNull(subCategory)) {
				SoftSubCategoryInfo softSubCategoryInfo=new SoftSubCategoryInfo();
				softSubCategoryInfo.setSoftSubCategoryName(subCategory.getSubCategoryName());
				SubSubCategory subSubCategory = subCategory.getSubSubCategories().stream()
						.filter(subsubcate -> subsubcate.getSubSubCategoryName().equalsIgnoreCase(subSubCategoryName))
						.findFirst().orElse(null);
				if (Objects.nonNull(subSubCategory)) {
					SoftSubSubCategory softSubSubCategory = new SoftSubSubCategory();
					softSubSubCategory.setId(subSubCategory.getId());
					softSubSubCategory.setDate(subSubCategory.getDate());
					softSubSubCategory.setDescription(subSubCategory.getDescription());
					softSubSubCategory.setSubSubCategoryName(subSubCategory.getSubSubCategoryName());
					softSubSubCategory.setUserName(subSubCategory.getUserName());
					subSubCategoryRepository.deleteById(subSubCategory.getId());
					subSubCategoryRepository.deleteSubSubCategory(subSubCategory.getId());
					softSubSubCategory.setCategoryInfo(softCategoryInfo);
					softSubSubCategory.setSubSoftCategoryInfo(softSubCategoryInfo);
					SoftSubSubCategory savedSoftSubSubCategory = softSubSubCategoryRepository.save(softSubSubCategory);
					if (Objects.nonNull(savedSoftSubSubCategory)) {
						check = true;
					}
				}
			}
		}

		return check;
	}

	@Override
	public boolean addsubSubCategory(String categoryName, String subCategoryName, SubSubCategory subSubCategory) {
		boolean check = false;
		if ((Objects.nonNull(subCategoryName)) && (Objects.nonNull(subSubCategory))) {
			Category category = categoryRepository.findByCategoryName(categoryName);
			if (Objects.nonNull(category)) {
				SubCategory subCategoryVo = category.getSubCategory().stream()
						.filter(subcategory -> subcategory.getSubCategoryName().equalsIgnoreCase(subCategoryName))
						.findFirst().orElse(null);
				SubSubCategory checkSubSubCategory = subCategoryVo.getSubSubCategories().stream()
						.filter(subSubcat -> subSubcat.getSubSubCategoryName()
								.equalsIgnoreCase(subSubCategory.getSubSubCategoryName()))
						.findFirst().orElse(null);
				if (Objects.nonNull(subCategoryVo) && !(Objects.nonNull(checkSubSubCategory))) {
					subCategoryVo.getSubSubCategories().add(subSubCategory);
					SubCategory categoryVo = subCategoryRepository.save(subCategoryVo);
					if (Objects.nonNull(categoryVo)) {
						check = true;
					}
				}
			}
		}
		return check;
	}

}
