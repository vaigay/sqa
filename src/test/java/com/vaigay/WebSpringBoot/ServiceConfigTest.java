package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
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
import com.vaigay.WebSpringBoot.Respository.SubjectRepository;
import com.vaigay.WebSpringBoot.Service.ServiceConfig;

@SpringBootTest
public class ServiceConfigTest {

	@Autowired
	private ServiceConfig serviceConfig;
	
	
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
	public void testAddConfigGreaterThan100() {
		List<ConfigScoreDetail> listConDetails = new ArrayList<ConfigScoreDetail>();
		serviceConfig.addConfig("abc", "140", listConDetails);
		assertTrue(listConDetails.size() == 0);
	}
	
	@Test
	public void testAddConfigSmallerThan0() {
		List<ConfigScoreDetail> listConDetails = new ArrayList<ConfigScoreDetail>();
		serviceConfig.addConfig("abc", "-20", listConDetails);
		assertTrue(listConDetails.size() == 0);
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
	public void testSaveConfigNotEqual100() {
		serviceConfig.saveConfig("40", "20", "25", "20", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullAttendance() {
		serviceConfig.saveConfig("", "20", "20", "20", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullExercise() {
		serviceConfig.saveConfig("20", "", "20", "20", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullPractice() {
		serviceConfig.saveConfig("20", "20", "", "20", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullTest() {
		serviceConfig.saveConfig("20", "20", "20", "", "40", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullExam() {
		serviceConfig.saveConfig("20", "20", "20", "40", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigFirstParamentEqual100() {
		serviceConfig.saveConfig("100", "", "", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertTrue(configScoreDetails.get(0).getPercent() == 100);
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigSecondParamentEqual100() {
		serviceConfig.saveConfig("", "100", "", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertTrue(configScoreDetails.get(0).getPercent() == 100);
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigThirdParamentEqual100() {
		serviceConfig.saveConfig("", "", "100", "", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertTrue(configScoreDetails.get(0).getPercent() == 100);
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigFouthParamentEqual100() {
		serviceConfig.saveConfig("", "", "", "100", "", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertTrue(configScoreDetails.get(0).getPercent() == 100);
		assertEquals(tmp, 100);
	}
	
	@Test
	@Transactional
	public void testSaveConfigFifthParamentEqual100() {
		serviceConfig.saveConfig("", "", "", "", "100", "1");
		Subject s = serviceConfig.getSubjectByid(1);
		List<ConfigScoreDetail> configScoreDetails = s.getConfigScore().getListConfig();
		int tmp = 0;
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			tmp += configScoreDetail.getPercent();
			assertTrue(configScoreDetail.getPercent() > 0);
			assertTrue(configScoreDetail.getPercent() <= 100);
		}
		assertTrue(configScoreDetails.get(0).getPercent() == 100);
		assertEquals(tmp, 100);
	}
	
	
}

