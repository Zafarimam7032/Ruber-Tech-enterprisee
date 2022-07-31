package com.catelog.soft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.soft.model.SoftSubSubCategoryBackup;

@Transactional
@Repository
public interface SoftSubSubCategoryBackupRepository extends JpaRepository<SoftSubSubCategoryBackup, Long>{

	@Modifying
	@Query(value = " delete FROM category.soft_sub_category_backup_soft_sub_sub_category_backup where soft_sub_category_backup_id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.soft_sub_sub_category_backup where id=:id",nativeQuery = true)
	public void deleteSoftSubSubCategoryBackup(@Param("id") long id);
}
