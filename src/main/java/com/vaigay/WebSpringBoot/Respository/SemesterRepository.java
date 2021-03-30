package com.vaigay.WebSpringBoot.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaigay.WebSpringBoot.Entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
	
}
