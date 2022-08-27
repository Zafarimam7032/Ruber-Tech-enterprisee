package com.catelog.soft.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.catelog.soft.model.SoftCategory;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface SoftCategoryApi {

	@GetMapping(path = "/get/retrive/category/{categoryName}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<SoftCategory> getDeletedSoftCategory(@PathVariable("categoryName") String categoryName);
}
