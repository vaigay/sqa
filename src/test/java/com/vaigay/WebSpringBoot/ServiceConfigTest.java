package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;
import com.vaigay.WebSpringBoot.Entity.Subject;
import com.vaigay.WebSpringBoot.Respository.ConfigScoreDetailRepository;
import com.vaigay.WebSpringBoot.Respository.SubjectRepository;
import com.vaigay.WebSpringBoot.Service.ServiceConfig;

@SpringBootTest
public class ServiceConfigTest {

	@Autowired
	private ServiceConfig serviceConfig;
	
	@Autowired
	private ConfigScoreDetailRepository configScoreDetailRepository;
	
	
	@Test
	public void testGetSubjectConfig() {
		List<Subject> subjects =  serviceConfig.getSubjectConfig();	
		assertTrue(subjects.size() > 0 );
	}
	
	@Test
	public void testGetSubjectByid() {
		Subject subject = serviceConfig.getSubjectByid(1);
		assertNotNull(subject);;
	}
	
	@Test
	public void testGetNullSubjectByid() {
		Subject subject = serviceConfig.getSubjectByid(-11);
		assertNull(subject);;
	}
	
	@Test
	public void testAddConfig() {
		List<ConfigScoreDetail> listConDetails = new ArrayList<ConfigScoreDetail>();
		serviceConfig.addConfig("abc", "14", listConDetails);
		assertTrue(listConDetails.size() == 1);
	}
	
	
	@Test
	public void testAddConfigNull() {
		List<ConfigScoreDetail> listConDetails = new ArrayList<ConfigScoreDetail>();
		serviceConfig.addConfig("abc", "", listConDetails);
		assertTrue(listConDetails.size() == 0);
	}
	
	@Test
	@Transactional
	public void testSaveConfig() {
		serviceConfig.saveConfig("10", "20", "20", "20", "30", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		assertEquals(configScoreDetails.get(0).getPercent(),10 );
		assertEquals(configScoreDetails.get(1).getPercent(),20 );
		assertEquals(configScoreDetails.get(2).getPercent(),20 );
		assertEquals(configScoreDetails.get(3).getPercent(),20 );
		assertEquals(configScoreDetails.get(4).getPercent(),30 );
	}
	
	
	@Test
	@Transactional
	public void testSaveConfigNullAttendance() {
		serviceConfig.saveConfig("", "20", "20", "20", "40", "1");
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
//			System.out.println(configScoreDetail.getName() + " " + configScoreDetail.getPercent());
			if(configScoreDetail.getName().equals("attendance"))
				assertFalse(true);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("practice"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("examFinal"))
				assertEquals(configScoreDetail.getPercent(), 40);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullExercise() {
		serviceConfig.saveConfig("20", "", "20", "20", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("exercise"))
				assertFalse(true);
			if(configScoreDetail.getName().equals("practice"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("examFinal"))
				assertEquals(configScoreDetail.getPercent(), 40);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullPractice() {
		serviceConfig.saveConfig("20", "20", "", "20", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("practice"))				
				assertFalse(true);
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("examFinal"))
				assertEquals(configScoreDetail.getPercent(), 40);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullTest() {
		serviceConfig.saveConfig("20", "20", "20", "", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("practice"))	
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("test"))				
				assertFalse(true);
			if(configScoreDetail.getName().equals("examFinal"))
				assertEquals(configScoreDetail.getPercent(), 40);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullExam() {
		serviceConfig.saveConfig("20", "20", "20", "40", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("practice"))	
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 40);
			if(configScoreDetail.getName().equals("examFinal"))
				assertFalse(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigFirstParamentEqual100() {
		serviceConfig.saveConfig("100", "", "", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 100);
			if(!configScoreDetail.getName().equals("attendance"))
				assertFalse(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigSecondParamentEqual100() {
		serviceConfig.saveConfig("", "100", "", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 100);
			if(!configScoreDetail.getName().equals("exercise"))
				assertFalse(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigThirdParamentEqual100() {
		serviceConfig.saveConfig("", "", "100", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("practice"))
				assertEquals(configScoreDetail.getPercent(), 100);
			if(!configScoreDetail.getName().equals("practice"))
				assertFalse(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfigFouthParamentEqual100() {
		serviceConfig.saveConfig("", "", "", "100", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 100);
			if(!configScoreDetail.getName().equals("test"))
				assertFalse(true);
		}
		
	}
	
	@Test
	@Transactional
	public void testSaveConfigFifthParamentEqual100() {
		serviceConfig.saveConfig("", "", "", "", "100", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("examFinal"))
				assertEquals(configScoreDetail.getPercent(), 100);
			if(!configScoreDetail.getName().equals("examFinal"))
				assertFalse(true);
		}
	}
	
	
}

