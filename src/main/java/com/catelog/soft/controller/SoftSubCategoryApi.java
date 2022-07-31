package com.catelog.soft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.catelog.soft.model.SoftSubSubCategory;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface SoftSubCategoryApi {

	@GetMapping(path = "/get/retrive/category/{categoryName}/subcategory/{subCategoryName}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<SoftSubSubCategory> getDeletedSubCategory(@PathVariable("categoryName") String categoryName,
			@PathVariable("subCategoryName") String subCategoryName);

}
