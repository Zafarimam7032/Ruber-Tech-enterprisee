package com.catelog.api;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.catelog.model.Category;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface CategoryApi {

	@GetMapping(path = "/get/all")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200" ,description  = "succesfull retrival"),
			@ApiResponse(responseCode = "500" ,description  = "Not Found"),
			@ApiResponse(responseCode = "404" ,description  = "service not found")
	})
	public ResponseEntity<List<Category>> getAllCategory();
	
	@GetMapping(path = "/get/{categoryName}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200" ,description  = "succesfull retrival"),
			@ApiResponse(responseCode = "500" ,description  = "Not Found"),
			@ApiResponse(responseCode = "404" ,description  = "service not found")
	})
	public ResponseEntity<Category> getCategoryByName(@PathVariable(value = "categoryName",required = true) String categoryName);

	@PostMapping(path = "/add/category")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "success"),
		@ApiResponse(responseCode = "500",description = "server error"),
		@ApiResponse(responseCode = "404",description = "service not found")
	})
	public ResponseEntity<Boolean> addCategory(@RequestBody(required = true) Category category);
	
	@PutMapping(path = "/update/category/oldcategoryname/{oldCategoryName}/newcategoryname/{newCategoryName}")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "success"),
		@ApiResponse(responseCode = "500",description = "server error"),
		@ApiResponse(responseCode = "404",description = "service not found")
	}) 
	public ResponseEntity<Boolean> updateCategory(@PathVariable("oldCategoryName") String oldCategoryName,@PathVariable("newCategoryName") String newCategoryName);
	@DeleteMapping(path = "/delete/category/categoryname/{categoryName}")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "success"),
		@ApiResponse(responseCode = "500",description = "server error"),
		@ApiResponse(responseCode = "404",description = "service not found")
	}) 
	public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryName") String categoryName);
}
