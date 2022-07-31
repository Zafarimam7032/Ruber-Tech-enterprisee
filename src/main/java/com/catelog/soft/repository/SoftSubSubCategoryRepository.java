package com.catelog.soft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.soft.model.SoftSubSubCategory;

@Transactional
@Repository
public interface SoftSubSubCategoryRepository extends JpaRepository<SoftSubSubCategory, Long> {
	
	public SoftSubSubCategory findBySubSubCategoryName(String subSubCategoryName);
	
	@Modifying
	@Query(value  = "update  category.soft_sub_sub_category set category_info_id=0 where category_info_id=:id",nativeQuery = true)
	public void updateSoftCategoryId(@Param("id") long id);
	
	@Modifying
	@Query(value  = "update  category.soft_sub_sub_category set sub_category_info_id=0 where sub_category_info_id=:id",nativeQuery = true)
	public void updateSoftSubCategoryId(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.soft_sub_sub_category where id=:id",nativeQuery = true)
	public void deleteSoftSubSubCategory(@Param("id") long id);

}
