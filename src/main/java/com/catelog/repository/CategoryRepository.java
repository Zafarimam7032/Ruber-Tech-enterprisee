package com.catelog.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.catelog.model.Category;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Category findByCategoryName(String categoryName);

	@Modifying
	@Query(value  = "delete from category_sub_categories where category_id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.category where id=:id",nativeQuery = true)
	public void deleteCategory(@Param("id") long id);
}
