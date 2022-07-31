package com.catelog.soft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catelog.soft.model.SoftCategory;
import com.catelog.soft.service.impl.SoftCategoryServiceImpl;

@RestController
@RequestMapping(path = "${api.version}")
public class SoftCategoryController implements SoftCategoryApi {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SoftCategoryServiceImpl softCategoryService;

	@Override
	public ResponseEntity<SoftCategory> getDeletedSoftCategory(String categoryName) {
		try {
			boolean retriveSoftCategoryinformation = softCategoryService.retriveCategory(categoryName);
			if (retriveSoftCategoryinformation) {
				logger.info("category retrived successfully");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				logger.debug("unable retrived to add the category");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
