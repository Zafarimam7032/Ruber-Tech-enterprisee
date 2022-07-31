package com.catelog.soft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.soft.model.SoftSubCategoryBackup;

@Transactional
@Repository
public interface SoftSubCategoryBackupRepository extends JpaRepository<SoftSubCategoryBackup, Long> {

	@Modifying
	@Query(value  = "delete from category.soft_category_soft_sub_category_backup where soft_sub_category_backup_id=:id",nativeQuery = true)
	public void deleteById(@Param("id") long id);
	
	@Modifying
	@Query(value = "delete  from  category.soft_sub_category_backup where id=:id",nativeQuery = true)
	public void deleteSoftSubCategoryBackup(@Param("id") long id);


}
