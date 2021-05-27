package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Service.ServiceReport;

@SpringBootTest
public class ServiceReportTest {

	@Autowired
	private ServiceReport serviceReport;

	@Autowired
	private MajorRepository majorRepository;

	@Test
	public void testGetAllUser() {
		List<User> users = serviceReport.getAllUser();
		assertTrue(users.size() > 0);
	}

	@Test
	public void testListAllCourse() {
		List<Course> courses = serviceReport.getAllCourses();
		assertTrue(courses.size() > 0);
	}

	@Test
	public void testListAllMajor() {
		List<Major> majors = serviceReport.getAllMajor();
		assertTrue(majors.size() > 0);
	}

	@Test
	public void testGetReportAllMess() {
		List<String> listMess = serviceReport.getReportAllMess();
		assertTrue(listMess.size() > 0);
	}

	@Test
	public void testGetMessCourse() {
		List<String> listMess = serviceReport.getMessCourse((long) 2);
		assertTrue(listMess.size() > 2);
	}

	@Test
	public void testGetMessCourseNoStudent() {
		List<String> listMess = serviceReport.getMessCourse((long) 3);
		System.out.println(listMess.size());
		assertTrue(listMess.size() == 1);
	}

	@Test
	public void testGetMessInvalidCourse() {
		try {
			List<String> listMess = serviceReport.getMessCourse((long) -2);
			assertTrue(listMess.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetMessMajor() {
		List<String> listMess = serviceReport.getMessMajor(1);
		assertTrue(listMess.size() > 1);
	}

	@Test
	public void testGetMessMajorNoStudent() {
		List<String> listMess = serviceReport.getMessMajor(4);
		assertTrue(listMess.size() == 1);
	}

	@Test
	public void testGetMessInvalidMajor() {
		try {
			List<String> listMess = serviceReport.getMessMajor(-11);
			assertTrue(listMess.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetMessCourseAndMajor() {
		List<String> listMess = serviceReport.getReportMessByField(1, 1);
		assertTrue(listMess.size() > 0);
	}

	@Test
	public void testGetMessCourseAndInvalidMajor() {
		try {
			List<String> listMess = serviceReport.getReportMessByField(1, -11);
			assertTrue(listMess.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetMessInvalidCourseAndMajor() {
		try {
			List<String> listMess = serviceReport.getReportMessByField(-11, 1);
			assertTrue(listMess.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

}
