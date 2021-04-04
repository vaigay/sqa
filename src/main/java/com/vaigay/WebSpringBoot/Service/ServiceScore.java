package com.vaigay.WebSpringBoot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;
import com.vaigay.WebSpringBoot.Entity.Semester;
import com.vaigay.WebSpringBoot.Entity.Subject;
import com.vaigay.WebSpringBoot.Entity.SubjectClass;
import com.vaigay.WebSpringBoot.Respository.SemesterRepository;
import com.vaigay.WebSpringBoot.Respository.SubjectClassRepository;
import com.vaigay.WebSpringBoot.Respository.SubjectRepository;

@Service
public class ServiceScore {
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SubjectClassRepository suClassRepository;
	
	
	public List<Semester> getAllSemester(){
		return semesterRepository.findAll();
	}
	
	public List<Subject> getSubjectBySemester(long idS){
		return subjectRepository.findDistinctBySubjectClass_SemesterId(idS);
	}
	
	public List<SubjectClass> getSubjectClassInSemester(long id,long ids){
		List<SubjectClass> x =  suClassRepository.findBySubjectIdAndSemesterId(id, ids);
		return x;
	}
	
	public String getNameSubject(long id) {
		return subjectRepository.getNameSubject(id);
	}
	
	public SubjectClass getOneSubjectClass(long id) {
		return suClassRepository.getOne(id);
	}
	
	public List<String> getHeaderTableScore(ConfigScore configScore){
		List<String> header = new ArrayList<String>();
		List<ConfigScoreDetail> listConfigScoreDetails = configScore.getListConfig();
		getHead(header,"attendance",listConfigScoreDetails,"Chuyên cần");
		getHead(header,"test",listConfigScoreDetails,"Kiểm tra");
		getHead(header,"practice",listConfigScoreDetails,"Thực hành");
		getHead(header,"exercise",listConfigScoreDetails,"Bài tập");
		getHead(header,"examFinal",listConfigScoreDetails,"Thi");
		return header;
	}

	private void getHead(List<String> header, String string, List<ConfigScoreDetail> listConfigScoreDetails, String replace) {
		for(ConfigScoreDetail detail : listConfigScoreDetails) {
			if(detail.getName().equals(string)) {
				String add = replace + " (" + detail.getPercent() + ")";
				header.add(add);
				return;
			}
		}
		
	}
}
