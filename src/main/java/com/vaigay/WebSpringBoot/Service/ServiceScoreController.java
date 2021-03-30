package com.vaigay.WebSpringBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.Semester;
import com.vaigay.WebSpringBoot.Entity.Subject;
import com.vaigay.WebSpringBoot.Respository.SemesterRepository;
import com.vaigay.WebSpringBoot.Respository.SubjectRespository;

@Service
public class ServiceScoreController {
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@Autowired
	private SubjectRespository subjectRespository;
	
	public List<Semester> getAllSemester(){
		return semesterRepository.findAll();
	}
	
	public List<Subject> getSubjectBySemester(long idS){
		return subjectRespository.findBySubjectClass_SemesterId(idS);
	}
}
