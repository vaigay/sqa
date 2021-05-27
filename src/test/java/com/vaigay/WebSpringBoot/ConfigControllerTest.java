package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;


@SpringBootTest
@AutoConfigureMockMvc
public class ConfigControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testViewSubject() throws Exception{
		mockMvc.perform(get("/config"))
		.andExpect(status().isOk())
		.andExpect(view().name("config"))
		.andExpect(model().attributeExists("listSubject"));
	}
	
	@Test
	public void testViewConfigSubject() throws Exception{
		mockMvc.perform(get("/subjectConfig/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("configDetail"))
		.andExpect(model().attributeExists("subject","idSubject"));
	}
	
	@Test
	public void testViewConfigSubjectWithInvalidSubject() {
		try {
			mockMvc.perform(get("/subjectConfig/13"))
			.andExpect(status().is5xxServerError());
		} catch (Exception e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testSaveConfig() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "10")
		        .param("exercise", "")
		        .param("practice", "20")
		        .param("test", "70")
		        .param("examFinal", "")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
	}
}
