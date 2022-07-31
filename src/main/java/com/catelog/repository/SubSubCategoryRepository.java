package com.catelog.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.model.SubSubCategory;

@Transactional
@Repository
public interface SubSubCategoryRepository extends JpaRepository<SubSubCategory, Long> {

	public SubSubCategory findBySubSubCategoryName(String subSubCategoryName);
	
	@Modifying
	@Query(value  = "delete from sub_category_sub_sub_categories where sub_sub_categories_id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.sub_sub_category where id=:id",nativeQuery = true)
	public void deleteSubSubCategory(@Param("id") long id);
}
