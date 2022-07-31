package com.catelog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.catelog.model.SubSubCategory;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface SubSubCategoryApi {

	@GetMapping(path = "/all/subsubcategory")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<List<SubSubCategory>> getAllSubSubCategory();

	@GetMapping(path = "categor/{categoryName}/subcategory/{subCategory}/subsubcategory/{subSubCategoryName}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<SubSubCategory> getSubSubCategory(@PathVariable("categoryName") String categoryName,@PathVariable("subCategory") String subCategory, @PathVariable("subSubCategoryName") String subSubCategoryName);
	
	@PostMapping(path = "/add/subsubcategory/categoryname/{categoryName}/subsubcategoryname/{subSubCategoryName}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> addSubSubCategory(@PathVariable("categoryName") String categoryName, @PathVariable("subSubCategoryName") String subSubCategoryName, @RequestBody(required = true) SubSubCategory subSubCategory);
	
	@PutMapping(path = "/update/category/{categoryName}/subcategoryname/{subCategoryName}/oldsubsubcategoryname/{oldsubsubcategoryname}/subsubcategoory/{subsubcategoryName}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateSubSubCategory(@PathVariable("categoryName") String categoryName,@PathVariable("subCategoryName") String subCategoryName,@PathVariable("oldsubsubcategoryname")String oldSubSubCategoryName, @PathVariable("subsubcategoryName") String subSubCategoryName);
	
	@DeleteMapping(path = "delete/category/{categoryName}/subcategory/{subCategory}/subsubcategory/{subSubCategoryName}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "succesfull retrival"),
			@ApiResponse(responseCode = "500", description = "Not Found"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> deleteSubSubCategoryName(@PathVariable("categoryName") String categoryName,@PathVariable("subCategory") String subCategory,
			@PathVariable("subSubCategoryName") String subSubCategoryName);
	
}
