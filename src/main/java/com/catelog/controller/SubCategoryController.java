package com.catelog.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catelog.model.Category;
import com.catelog.model.SubCategory;
import com.catelog.service.impl.SubCategoryServiceImpl;

@RestController
@RequestMapping(path = "${api.version}")
public class SubCategoryController implements SubCategoryApi {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SubCategoryServiceImpl subCategoryService;

	@Override
	public ResponseEntity<List<SubCategory>> getAllSubCategoryNames() {
		try {
			List<SubCategory> subCategories = subCategoryService.getSubCategories();
			return ResponseEntity.of(Optional.of(subCategories));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<SubCategory> getSubCategory(String categoryName,String subCategoryName) {
		try {
			 SubCategory subCategory = subCategoryService.getSubCategory(categoryName,subCategoryName);
			return ResponseEntity.of(Optional.of(subCategory));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> addSubCateegory(String categoryName,SubCategory subCategory) {
		try {
			boolean addSubCategoryinfomartion = subCategoryService.addSubCategory(categoryName, subCategory);
			if (addSubCategoryinfomartion) {
				logger.info("Subcategory added successfully");
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				logger.debug("unable to add the category");
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> updateSubCategory(String categoryName,String oldSubcategoryName,String subCategoryName) {
		try {
			boolean updateSubCategoryinfomartion = subCategoryService.updateSubCategoryName(categoryName,oldSubcategoryName, subCategoryName);
			if (updateSubCategoryinfomartion) {
				logger.info("Subcategory updated successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to updated the Subcategory");
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	}

	@Override
	public ResponseEntity<Boolean> deleteSubCategory(String categoryName,String subcategoryName) {
		try {
			boolean deleteSubCategoryinfomartion = subCategoryService.deletesubCategory(categoryName,subcategoryName);
			if (deleteSubCategoryinfomartion) {
				logger.info("Subcategory deleted successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to delete the Subcategory");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
