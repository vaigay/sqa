package com.vaigay.WebSpringBoot.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaigay.WebSpringBoot.Entity.Major;

public interface MajorRepository extends JpaRepository<Major, Long>{
	
	@Query("SELECT major.shortName FROM Major major WHERE major.id = :id")
	String findShortNameMajorByID(@Param("id") long id);
}
