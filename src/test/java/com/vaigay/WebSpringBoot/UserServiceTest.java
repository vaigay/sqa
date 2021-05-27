package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.UserRepository;
import com.vaigay.WebSpringBoot.Service.ServiceUser;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
	
	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testGetAllUser() {
		List<User> listUsers = serviceUser.getAllUser();
		assertNotNull(listUsers);
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testListAllCourse() {
		List<Course> courses = serviceUser.listAllCourse();
		assertNotNull(courses);
		assertTrue(courses.size() > 0);
	}
	
	@Test
	public void testListAllMajor() {
		List<Major> majors = serviceUser.listAllMajor();
		assertNotNull(majors);
		assertTrue(majors.size() > 0);
	}
	
	@Test
	@Transactional
	public void testSaveUser() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertTrue(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutName() {
		try {
			User u = new User();
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			System.out.println(u);
			assertTrue(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutAddress() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			System.out.println(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutDateOfBirth() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutEmail() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithEmailInvalid() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("12312313123");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutStatus() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutMajor() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");			
			Course c = new Course();
			c.setId(1);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithInvalidMajor() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(100);
			Course c = new Course();
			c.setId(1);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithoutCourse() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			u.setMajor(m);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testSaveUserWithInvalidCourse() {
		try {
			User u = new User();
			u.setFullName("name");
			u.setAddress("address");
			u.setDateOfBirth(new Date(8,8,1999));
			u.setEmail("hai123@gmail.com");
			u.setStatus(1);
			Major m = new Major();
			m.setId(1);
			Course c = new Course();
			c.setId(100);
			u.setMajor(m);
			u.setCourse(c);
			serviceUser.saveUser(u);
			assertFalse(u.getId() > 0);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void testUpdateUserCode() {
		User u = userRepository.getOne((long) 1);
		User k = new User();
		k.setId(u.getId());
		k.setCourse(u.getCourse());
		k.setMajor(u.getMajor());
		serviceUser.updateCode(k);
		assertEquals(u.getCode(), k.getCode());
		
	}
	
	
	@Test
	public void testGetListUserByCode() {
		List<User> users = serviceUser.getListUserByCode("B17DCCN1");
		assertTrue(users.size() >  0);
	}
	
	@Test
	public void testGetListUserByInvalidCode() {
		List<User> users = serviceUser.getListUserByCode("xxxxxxx");
		assertTrue(users.size() ==  0);
	}
	
	@Test
	public void testGetListUserByNoCode() {
		List<User> users = serviceUser.getListUserByCode("");
		assertTrue(users.size() ==  0);
	}
	
	
	@Test
	public void testGetUserById() {
		User u = serviceUser.getUserById(1);
		assertNotNull(u);
	}
	
	@Test
	public void testGetUserByInvalidId() {
		try {
			User u = serviceUser.getUserById(150);
			assertNull(u);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	@Transactional
	public void testDeleteUser() {
		try {
			serviceUser.deleteUser(1);
			User u = userRepository.getOne((long) 1);
			assertEquals(u.getStatus(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testDeleteUserInvalid() {
		try {
			serviceUser.deleteUser(1);
			User u = userRepository.getOne((long) 100);
			assertEquals(u.getStatus(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
