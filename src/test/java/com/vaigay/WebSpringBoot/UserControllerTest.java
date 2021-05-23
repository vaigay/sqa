package com.vaigay.WebSpringBoot;






import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vaigay.WebSpringBoot.Controller.UserController;
import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRepository;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Respository.UserRepository;

import junit.framework.Assert;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MajorRepository majorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@Autowired
//	private MockHttpServletRequestBuilder mockRequest;
	
//	@InjectMocks
//	private UserController userController;
//	
//	@BeforeEach
//	public void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//	}
	
	
	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void testUserPageIndex() throws Exception {
		int size = (int)userRepository.countWithStatus(1);
		
		mockMvc.perform(get("/listUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("listUser"))
		.andExpect(model().attribute("listUser", hasSize(size)));
	}
	
	@Test
	public void testGetNewUser() throws Exception {
		int sizeMajor = (int) majorRepository.count();
		int sizeCourse = (int) courseRepository.count();

		mockMvc.perform(get("/newUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("newUser"))
		.andExpect(model().attribute("listMajor", hasSize(sizeMajor)))
		.andExpect(model().attribute("listCourse", hasSize(sizeCourse)));
	}
	
	@Test
	@Transactional
	public void testSaveUser() throws Exception {
		Course c = new Course();
		c.setId(1);
		Major m = new Major();
		m.setId(1);
		Date date = new Date(1999, 9, 9);
		User u = new User("Hai Phamxxxx",date,c,"Ha Dong","hai123@gmail.com",m,1);
		long before = userRepository.count();
		
		MockHttpServletRequestBuilder createMessage = post("/user/saveNew")
		        .param("fullName", u.getFullName())
		        .param("dateOfBirth", u.getDateOfBirth().toString())
		        .param("address", u.getAddress())
		        .param("email", u.getEmail())
		        .param("course.id", String.valueOf(u.getCourse().getId()))
		        .param("major.id", String.valueOf(u.getMajor().getId()));
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listUser"));
		
		long after = userRepository.count();
		assertTrue(after - before == 1);
	}
	
	@Test
	@Transactional
	public void testSaveEditFullName() throws Exception {
		User u = userRepository.getOne((long) 1);
		System.out.println(u.getId());
		User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
		MockHttpServletRequestBuilder createMessage = post("/saveEditUser")
				.param("id", String.valueOf(u.getId()))
		        .param("fullName", "abc")
		        .param("dateOfBirth", u.getDateOfBirth().toString())
		        .param("address", u.getAddress())
		        .param("email", u.getEmail())
		        .param("course.id", String.valueOf(u.getCourse().getId()))
		        .param("major.id", String.valueOf(u.getMajor().getId()));
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listUser"));
		User after = userRepository.getOne((long) 1);
		assertEquals(before.getId(),after.getId());
		assertNotEquals(before.getFullName(),after.getFullName());
		assertEquals(before.getDateOfBirth(),after.getDateOfBirth());
		assertEquals(before.getAddress(),after.getAddress());
		assertEquals(before.getEmail(),after.getEmail());
		assertEquals(before.getMajor().getId(),after.getMajor().getId());
		assertEquals(before.getCourse().getId(),after.getCourse().getId());
	}
	
	@Test
	@Transactional
	public void testSaveEditDateOfBirthName() throws Exception {
		User u = userRepository.getOne((long) 1);
		System.out.println(u.getId());
		User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
		MockHttpServletRequestBuilder createMessage = post("/saveEditUser")
				.param("id", String.valueOf(u.getId()))
		        .param("fullName", u.getFullName())
		        .param("dateOfBirth", new Date(11/11/1991).toString())
		        .param("address", u.getAddress())
		        .param("email", u.getEmail())
		        .param("course.id", String.valueOf(u.getCourse().getId()))
		        .param("major.id", String.valueOf(u.getMajor().getId()));
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listUser"));
		User after = userRepository.getOne((long) 1);
		assertEquals(before.getId(),after.getId());
		assertEquals(before.getFullName(),after.getFullName());
		assertNotEquals(before.getDateOfBirth(),after.getDateOfBirth());
		assertEquals(before.getAddress(),after.getAddress());
		assertEquals(before.getEmail(),after.getEmail());
		assertEquals(before.getMajor().getId(),after.getMajor().getId());
		assertEquals(before.getCourse().getId(),after.getCourse().getId());
	}
	
	@Test
	@Transactional
	public void testSaveEditAddress() throws Exception {
		User u = userRepository.getOne((long) 1);
		System.out.println(u.getId());
		User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
		MockHttpServletRequestBuilder createMessage = post("/saveEditUser")
				.param("id", String.valueOf(u.getId()))
		        .param("fullName", u.getFullName())
		        .param("dateOfBirth",u.getDateOfBirth().toString())
		        .param("address", "pamasd")
		        .param("email", u.getEmail())
		        .param("course.id", String.valueOf(u.getCourse().getId()))
		        .param("major.id", String.valueOf(u.getMajor().getId()));
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listUser"));
		User after = userRepository.getOne((long) 1);
		assertEquals(before.getId(),after.getId());
		assertEquals(before.getFullName(),after.getFullName());
		assertEquals(before.getDateOfBirth(),after.getDateOfBirth());
		assertNotEquals(before.getAddress(),after.getAddress());
		assertEquals(before.getEmail(),after.getEmail());
		assertEquals(before.getMajor().getId(),after.getMajor().getId());
		assertEquals(before.getCourse().getId(),after.getCourse().getId());
	}
	
	@Test
	@Transactional
	public void testSaveEditEmail() throws Exception {
		User u = userRepository.getOne((long) 1);
		System.out.println(u.getId());
		User before = new User(u.getId(),u.getFullName(),u.getCode(),u.getDateOfBirth(),u.getCourse(),u.getAddress(),u.getEmail(),u.getMajor(),u.getStatus());
		MockHttpServletRequestBuilder createMessage = post("/saveEditUser")
				.param("id", String.valueOf(u.getId()))
				.param("code", u.getCode())
		        .param("fullName", u.getFullName())
		        .param("dateOfBirth",u.getDateOfBirth().toString())
		        .param("address", u.getAddress())
		        .param("email", "haihai@gmail.com")
		        .param("course.id", String.valueOf(u.getCourse().getId()))
		        .param("major.id", String.valueOf(u.getMajor().getId()));
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listUser"));
		User after = userRepository.getOne((long) 1);
		System.out.println(after.getCode());
		System.out.println(after);
		assertEquals(before.getId(),after.getId());
		assertEquals(before.getFullName(),after.getFullName());
		assertEquals(before.getCode(),after.getCode() );
		assertEquals(before.getDateOfBirth(),after.getDateOfBirth());
		assertEquals(before.getAddress(),after.getAddress());
		assertNotEquals(before.getEmail(),after.getEmail());
		assertEquals(before.getMajor().getId(),after.getMajor().getId());
		assertEquals(before.getCourse().getId(),after.getCourse().getId());
	}
	
	@Test
	public void testGetListUserWithInvalidCode() throws Exception {


		mockMvc.perform(get("/findUserByCode").param("code","xadase123123" ))
		.andExpect(status().isOk())
		.andExpect(view().name("listUser"))
		.andExpect(model().attribute("listUser", hasSize(0)));

	}
	
	
	
}
