package com.catelog.soft.service.impl;

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
import com.catelog.soft.model.SoftCategory;
import com.catelog.soft.model.SoftSubCategory;
import com.catelog.soft.model.SoftSubCategoryBackup;
import com.catelog.soft.model.SoftSubSubCategory;
import com.catelog.soft.model.SoftSubSubCategoryBackup;
import com.catelog.soft.repository.SoftCategoryRepository;
import com.catelog.soft.repository.SoftSubCategoryBackupRepository;
import com.catelog.soft.repository.SoftSubSubCategoryBackupRepository;
import com.catelog.soft.service.SoftCategoryService;

@Service
public class SoftCategoryServiceImpl implements SoftCategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoftCategoryServiceImpl.class);

	@Autowired
	private SoftCategoryRepository softCategoryRepository;

	@Autowired
	private CategoryRepository catelogRepository;

	@Autowired
	private SoftSubCategoryBackupRepository softSubCategoryBackupRepository;
	
	@Autowired
	private SoftSubSubCategoryBackupRepository softSubSubCategoryBackupRepository;
	
	@Override
	public boolean retriveCategory(String CategoryName) {
		boolean check = false;
		SoftCategory softCategory = softCategoryRepository.findByCategoryName(CategoryName);
		if(softCategory!=null)
		{
			Category category=new Category();
			category.setId(softCategory.getId());
			category.setUserName(softCategory.getUserName());
			category.setCategoryName(softCategory.getCategoryName());
			category.setDescription(softCategory.getDescription());
			category.setDate(softCategory.getDate());
			List<SubCategory> subCategories=new ArrayList<>();
			List<SubSubCategory> subSubCategories=new ArrayList<>();
			List<SoftSubCategoryBackup> softSubCategories = softCategory.getSoftSubCategoryBackup();
			if(softSubCategories.size()>0)
			{
				for(SoftSubCategoryBackup softSubCategory :softSubCategories)
				{
					SubCategory subCategory=new SubCategory();
					List<SoftSubSubCategoryBackup> softSububSubCategories = softSubCategory.getSoftSubSubCategoryBackup();
					if(softSububSubCategories.size()>0)
					{
						for (SoftSubSubCategoryBackup softSubSubCategory:softSububSubCategories)
						{
							SubSubCategory subSubCategory=new SubSubCategory();
							subSubCategory.setId(softSubSubCategory.getId());
							subSubCategory.setUserName(softSubSubCategory.getUserName());
							subSubCategory.setDate(softSubSubCategory.getDate());
							subSubCategory.setSubSubCategoryName(softSubSubCategory.getSubSubCategoryName());
							subSubCategory.setDescription(softSubSubCategory.getDescription());
							subSubCategories.add(subSubCategory);
							softSubCategoryBackupRepository.deleteById(softSubSubCategory.getId());
							softSubSubCategoryBackupRepository.deleteById(softSubCategory.getId());
							softSubCategoryBackupRepository.deleteSoftSubCategoryBackup(softSubSubCategory.getId());
						}
						subCategory.setSubSubCategories(subSubCategories);
					}
					subCategory.setId(softSubCategory.getId());
					subCategory.setUserName(softSubCategory.getUserName());
					subCategory.setDate(softSubCategory.getDate());
					subCategory.setDescription(softSubCategory.getDescription());
					subCategory.setSubCategory(softSubCategory.getSubCategory());
					subCategories.add(subCategory);
					softSubSubCategoryBackupRepository.deleteSoftSubSubCategoryBackup(softSubCategory.getId());
				}
				
			}
			softCategoryRepository.deleteById(softCategory.getId());
			category.setSubCategory(subCategories);
			Category savedCategory = catelogRepository.save(category);
			if(Objects.nonNull(savedCategory))
			{
				check=true;
			}
		}
		return check;
	}

}
