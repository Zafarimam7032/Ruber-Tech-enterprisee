package com.catelog.soft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catelog.soft.model.SoftSubCategoryInfo;

@Transactional
@Repository
public interface SoftSubCategoryInformationRepository extends JpaRepository<SoftSubCategoryInfo, Long> {

	@Modifying
	@Query(value ="delete FROM category.soft_sub_category_info where id=:id",nativeQuery = true )
	public void deleteById(@Param("id") long id);
}
