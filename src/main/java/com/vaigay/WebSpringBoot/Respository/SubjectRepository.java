package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaigay.WebSpringBoot.Entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
	List<Subject> findDistinctBySubjectClass_SemesterId(long idS);
	
	@Query("SELECT sub.name fROM Subject sub WHERE sub.id = :id")
	String getNameSubject(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Subject s set s.configScore.id = :idc WHERE s.id = :ids")
	void updateConfigId(@Param("idc") long idc,@Param("ids") long ids);
	
}
