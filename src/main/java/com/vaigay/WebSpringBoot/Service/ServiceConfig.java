package com.vaigay.WebSpringBoot.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;
import com.vaigay.WebSpringBoot.Entity.Subject;
import com.vaigay.WebSpringBoot.Respository.ConfigScoreDetailRepository;
import com.vaigay.WebSpringBoot.Respository.ConfigScoreRepository;
import com.vaigay.WebSpringBoot.Respository.SubjectRepository;

@Service
public class ServiceConfig {
	
	@Autowired
	private ConfigScoreRepository conRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ConfigScoreDetailRepository configScoreDetailRepository;
	
	public List<Subject> getSubjectConfig(){
		return subjectRepository.findAll();
	}
	
	public void setModelScoreConfig (long id,Model model) {
		List<ConfigScoreDetail> listConfigScore = configScoreDetailRepository.findByConfigScore_SubjectId(id);
		for(ConfigScoreDetail coDetail : listConfigScore) {
			model.addAttribute(coDetail.getName(), coDetail.getPercent());
		}
	}
	
	@Transactional
	public void saveConfig(String attendance,String exercise,String practice,String test,String examFinal, String idSubject ) {
		ConfigScore config = new ConfigScore();
		List<ConfigScoreDetail> listConDetails = new ArrayList<ConfigScoreDetail>();
		addConfig("attendance",attendance,listConDetails);
		addConfig("exercise",exercise,listConDetails);
		addConfig("practice",practice,listConDetails);
		addConfig("test",test,listConDetails);
		addConfig("examFinal",examFinal,listConDetails);
		config.setListConfig(listConDetails);
		for(ConfigScoreDetail configScoreDetai : listConDetails) 
			configScoreDetai.setConfigScore(config);
		conRepository.save(config);
		subjectRepository.updateConfigId(config.getId(),Long.parseLong(idSubject));
	}

	public void addConfig(String name, String percent, List<ConfigScoreDetail> listConDetails) {
		if(!percent.equals("")) {
			ConfigScoreDetail configScoreDetail = new ConfigScoreDetail();
			configScoreDetail.setName(name);
			configScoreDetail.setPercent(Integer.parseInt(percent));
			listConDetails.add(configScoreDetail);
		}
		
	}
	
	public Subject getSubjectByid(long id) {
		return subjectRepository.findById(id).orElse(null);
	}
	
	
}
