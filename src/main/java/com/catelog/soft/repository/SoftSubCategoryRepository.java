package com.catelog.soft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.soft.model.SoftSubCategory;

@Transactional
@Repository
public interface SoftSubCategoryRepository extends JpaRepository<SoftSubCategory, Long> {
	
	public SoftSubCategory findBySubCategory(String subCategoryName);
    
	@Modifying
	@Query(value = "update  category.soft_sub_category set category_info_id=0 where category_info_id=:id", nativeQuery = true)
	public void updateById(@Param("id") long id);
	@Modifying
	@Query(value = "delete from category.soft_sub_category where id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
}
