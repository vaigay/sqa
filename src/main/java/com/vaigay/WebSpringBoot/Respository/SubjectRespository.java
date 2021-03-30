package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaigay.WebSpringBoot.Entity.Subject;

public interface SubjectRespository extends JpaRepository<Subject, Long>{
	List<Subject> findBySubjectClass_SemesterId(long idS);
}
