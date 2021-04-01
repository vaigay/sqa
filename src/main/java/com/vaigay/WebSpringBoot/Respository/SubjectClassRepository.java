package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaigay.WebSpringBoot.Entity.SubjectClass;



public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long>{
	List<SubjectClass> findBySubjectIdAndSemesterId(long id,long ids);
}
