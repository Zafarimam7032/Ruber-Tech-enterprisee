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
import com.catelog.model.SubSubCategory;
import com.catelog.service.impl.SubSubCategoryServiceImpl;

@RestController
@RequestMapping(path = "${api.version}")
public class SubSubCategoryController implements SubSubCategoryApi {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SubSubCategoryServiceImpl subSubCategoryService;

	@Override
	public ResponseEntity<List<SubSubCategory>> getAllSubSubCategory() {
		try {
			logger.info("getting all SubSubcategory ");
			 List<SubSubCategory> subSubCategories = subSubCategoryService.getSubSubCategories();
			logger.debug("all category :" + subSubCategories.toString());
			return ResponseEntity.of(Optional.of(subSubCategories));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<SubSubCategory> getSubSubCategory(String categoryName,String subCategory,String subSubCategoryName) {
		try {
			 SubSubCategory subSubCategory = subSubCategoryService.getSubSubCategory(categoryName,subCategory,subSubCategoryName);
			return ResponseEntity.of(Optional.of(subSubCategory));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> addSubSubCategory(String categoryName,String subSubCategoryName, SubSubCategory subSubCategory) {
		try {
			boolean addSubSubCategoryinfomartion = subSubCategoryService.addsubSubCategory(categoryName,subSubCategoryName, subSubCategory);
			if (addSubSubCategoryinfomartion) {
				logger.info("subsubcategory added successfully");
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				logger.debug("unable to add the subsubcategory");
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> updateSubSubCategory(String categoryName,String subCategoryName,String oldSubSubCategoryName,String subSubCategoryName) {
		try {
			boolean updateSubSubCategoryinfomartion = subSubCategoryService.updatesubSubCategoryName(categoryName,subCategoryName,oldSubSubCategoryName,subSubCategoryName);
			if (updateSubSubCategoryinfomartion) {
				logger.info("subsubcategory added successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to add the subsubcategory");
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteSubSubCategoryName(String categoryName,String subCategoryName,String subSubCategoryName) {
		try {
			boolean deletesubsubCategoryinfomartion = subSubCategoryService.deletesubSubCategoryName(categoryName,subCategoryName,subSubCategoryName);
			if (deletesubsubCategoryinfomartion) {
				logger.info("subsubcategory added successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to add the subsubcategory");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
