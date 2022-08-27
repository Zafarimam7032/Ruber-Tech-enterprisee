package com.catelog.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.catelog.model.Category;
import com.catelog.model.SubCategory;
import com.catelog.model.SubSubCategory;
import com.catelog.repository.CategoryRepository;
import com.catelog.service.impl.CatelogServiceImpl;

@ExtendWith(MockitoExtension.class)
class CatelogServiceImplTest {

	@InjectMocks
	CatelogServiceImpl catelogService;
	
	@Mock
	private CategoryRepository catelogRepository;

	
	
	
	@Test
	public void getAllCategoryTest()
	{
		Category category=new Category();
		SubCategory subCategory=new SubCategory();
		SubSubCategory subSubCategory=new SubSubCategory();
		List<Category> categories=new ArrayList<>();
		List<SubCategory> subCategories=new ArrayList<>();
		List<SubSubCategory> subSubCategories=new ArrayList<>();
		subSubCategory.setId(1);
		subSubCategory.setDate(LocalDate.now());
		subSubCategory.setDescription("test subsubcategory");
		subSubCategory.setSubSubCategoryName("test");
		subSubCategory.setUserName("test user");
		subSubCategories.add(subSubCategory);
		subCategory.setSubSubCategories(subSubCategories);
		subSubCategory.setId(1);
		subSubCategory.setDate(LocalDate.now());
		subCategory.setDescription("test subCategory");
		subCategory.setSubCategory("test subcategory name");
		subCategory.setUserName("test");
		subCategories.add(subCategory);
		category.setSubCategory(subCategories);
		category.setCategoryName("test category");
		category.setDescription("test category infotmation");
		category.setId(1);
		category.setUserName("test user name");
		category.setDate(LocalDate.now());
		categories.add(category);
		when(catelogRepository.findAll()).thenReturn(categories);
		lenient().when(catelogService.getAllCategory()).thenReturn(categories);

		
		
		
		//when()
	}

}
