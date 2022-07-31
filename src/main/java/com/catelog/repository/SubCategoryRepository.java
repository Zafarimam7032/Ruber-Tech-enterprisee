package com.catelog.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.model.SubCategory;

@Repository
@Transactional
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	public SubCategory findBysubCategoryName(String subCategoryName);
	
	@Modifying
	@Query(value  = "delete from category_sub_categories where sub_categories_id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.sub_category where id=:id",nativeQuery = true)
	public void deleteSubCategory(@Param("id") long id);

}
