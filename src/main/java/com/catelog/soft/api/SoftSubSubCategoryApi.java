package com.catelog.soft.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.catelog.soft.model.SoftSubSubCategory;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface SoftSubSubCategoryApi {

	@GetMapping(path = "/get/retrive/categoryname/{CategoryName}/subcategoryname/{subCategoryName}/subsubcategory/{subSubCategoryName}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<SoftSubSubCategory> getDeletedSubSubCategory(@PathVariable("CategoryName") String CategoryName,@PathVariable("subCategoryName") String subCategoryName,
			@PathVariable("subSubCategoryName") String subSubCategoryName);

}
