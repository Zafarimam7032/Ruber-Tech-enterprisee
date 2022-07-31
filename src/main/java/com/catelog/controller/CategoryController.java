package com.catelog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catelog.model.Category;
import com.catelog.service.CatelogService;

@RestController
@RequestMapping(path = "${api.version}")
public class CategoryController implements CategoryApi {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CatelogService catelogService;

	@Override
	public ResponseEntity<List<Category>> getAllCategory() {
		try {
			logger.info("getting all category ");
			List<Category> allCategoryDetails = catelogService.getAllCategory();
			logger.debug("all category :" + allCategoryDetails.toString());
			return ResponseEntity.of(Optional.of(allCategoryDetails));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Category> getCategoryByName(String categoryName) {
		try {
			Category category = catelogService.getCategoryByCategoryName(categoryName);
			return ResponseEntity.of(Optional.of(category));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> addCategory(Category category) {
		try {
			boolean addCategoryinfomartion = catelogService.addCategory(category);
			if (addCategoryinfomartion) {
				logger.info("category added successfully");
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
	public ResponseEntity<Boolean> updateCategory(String oldCategoryName, String newCategoryName) {
		try {
			boolean updateCategoryinfomartion = catelogService.updateCategoryName(newCategoryName, oldCategoryName);
			if (updateCategoryinfomartion) {
				logger.info("category added successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to add the category");
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteCategory(String categoryName) {
		try {
			boolean deleteCategoryinfomartion = catelogService.deleteCategoryName(categoryName);
			if (deleteCategoryinfomartion) {
				logger.info("category added successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable to add the category");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
