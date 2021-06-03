package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
public class ServiceUserTest {
	
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
			User after = userRepository.getOne(u.getId());
			assertEquals(after, u);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithName() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			u.setFullName("1234");
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertNotEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithDateOfBirth() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			u.setDateOfBirth(new Date(8,8,1999));
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertNotEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithAdress() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			u.setAddress("new Address");
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertNotEquals(before.getAddress(), after.getAddress());
			assertEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithEmail() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			u.setEmail("email@email.123");
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertNotEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithInvalidEmail() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			u.setEmail("email@email.123");
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
			assertFalse(true);
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithMajor() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			Major m = new Major();
			m.setId(1);
			if(before.getMajor().getId() == 1)
				m.setId(2);
			u.setMajor(m);
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertNotEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertEquals(before.getCourse(), after.getCourse());
			assertNotEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	@Transactional
	public void testSaveEditUserWithCourse() {
		try {
			User u = userRepository.getOne((long) 1);
			User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
			Course c = new Course();
			c.setId(1);
			if(before.getCourse().getId() == 1)
				c.setId(2);
			u.setCourse(c);
			serviceUser.saveUser(u);
			User after = userRepository.getOne((long) 1);
			assertEquals(before.getId(), after.getId());
			assertEquals(before.getFullName(), after.getFullName());
			assertEquals(before.getAddress(), after.getAddress());
			assertNotEquals(before.getCode(), after.getCode());
			assertEquals(before.getStatus(), after.getStatus());
			assertEquals(before.getDateOfBirth(), after.getDateOfBirth());
			assertEquals(before.getEmail(), after.getEmail());
			assertNotEquals(before.getCourse(), after.getCourse());
			assertEquals(before.getMajor(), after.getMajor());
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
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
		User after = userRepository.getOne((long)1);
		assertEquals(after.getCode(), k.getCode());
		
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
			User after = userRepository.getOne((long) 1);
			assertEquals(after.getStatus(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testDeleteUserInvalid() {
		try {
			serviceUser.deleteUser(20);
			User u = userRepository.getOne((long) 100);
			User after = userRepository.getOne((long) 1);
			assertEquals(after.getStatus(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
