package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;
import com.vaigay.WebSpringBoot.Entity.Semester;
import com.vaigay.WebSpringBoot.Entity.Subject;
import com.vaigay.WebSpringBoot.Entity.SubjectClass;
import com.vaigay.WebSpringBoot.Service.ServiceScore;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceScoreTest {

	@Autowired
	private ServiceScore serviceScore;

	@Test
	public void testGetAllSemester() {
		List<Semester> semesters = serviceScore.getAllSemester();
		assertNotNull(semesters);
	}

	@Test
	public void testGetSubjectBySemester() {
		List<Subject> subjects = serviceScore.getSubjectBySemester(1);
		assertTrue(subjects.size() > 0);
	}

	@Test
	public void testGetSubjectByInvalidSemester() {
		List<Subject> subjects = serviceScore.getSubjectBySemester(2);
		assertTrue(subjects.size() == 0);
	}

	@Test
	public void testGetSubjectClassInSemester() {
		List<SubjectClass> subjectClasses = serviceScore.getSubjectClassInSemester(1, 1);
		assertNotNull(subjectClasses);
	}

	@Test
	public void testGetSubjectClassInInvalidSemester() {
		List<SubjectClass>subjectClasses = serviceScore.getSubjectClassInSemester(1, 3);
		assertTrue(subjectClasses.size() == 0);
	}
	
	@Test
	public void testGetInvalidSubjectClassInSemester() {
		List<SubjectClass>subjectClasses = serviceScore.getSubjectClassInSemester(100, 1);
		assertTrue(subjectClasses.size() == 0);
	}
	
	@Test
	public void testGetNameSubject(){
		String name = serviceScore.getNameSubject(1);
		assertTrue(name.length() > 0);
	}
	
	@Test
	public void testGetNameOfInvalidSubject(){
		try {
			String name = serviceScore.getNameSubject(100);
			assertTrue(name.length() == 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
	
	@Test
	public void getOneSubjectClass() {
		SubjectClass subjectClass = serviceScore.getOneSubjectClass(1);
		assertNotNull(subjectClass);
	}
	
	@Test
	public void testGetOneSubjectClass() {
		try {
			SubjectClass subjectClass = serviceScore.getOneSubjectClass(1);
			assertNull(subjectClass);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetHeaderTableScore() {
		ConfigScore configScore = new ConfigScore();
		ConfigScoreDetail detail1 = new ConfigScoreDetail();
		detail1.setName("attendance");
		detail1.setPercent(20);
		ConfigScoreDetail detail2 = new ConfigScoreDetail();
		detail2.setName("test");
		detail2.setPercent(50);
		List<ConfigScoreDetail> list = new ArrayList<ConfigScoreDetail>();
		list.add(detail1);
		list.add(detail2);
		configScore.setListConfig(list);
		List<String> header = serviceScore.getHeaderTableScore(configScore);
		assertEquals(header.size(), 2);
	}
	
	
}
