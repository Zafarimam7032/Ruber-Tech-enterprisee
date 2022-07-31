package com.catelog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.catelog.model.SubCategory;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface SubCategoryApi {

	@GetMapping(path = "/all/subcategory")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<List<SubCategory>> getAllSubCategoryNames();

	@GetMapping(path = "/category/{categoryName}/subcategory/{subCategoryName}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<SubCategory> getSubCategory(@PathVariable("categoryName") String categoryName, @PathVariable(name = "subCategoryName") String subCategoryName);

	@PostMapping(path = "/add/subcategory/cateegoryname/{categoryName}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> addSubCateegory(@PathVariable("categoryName") String categoryName, @RequestBody(required = true) SubCategory subCategory);

	@PutMapping(path = "update/category/{categoryName}/oldsubcategory/{oldSubcategoryName}/subcategoryname/{subcategoryName}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateSubCategory(@PathVariable(value = "categoryName") String categoryName,
			@PathVariable("oldSubcategoryName") String oldSubcategoryName,@PathVariable(value = "subcategoryName") String subCategoryName);

	@DeleteMapping(path = "delete/category/{categoryName}/subcategory/{subCategoryName}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> deleteSubCategory(@PathVariable("categoryName") String categoryName,@PathVariable("subCategoryName") String subcategoryName);
}
