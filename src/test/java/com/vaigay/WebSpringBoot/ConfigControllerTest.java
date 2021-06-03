package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;
import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.ConfigScoreDetailRepository;
import com.vaigay.WebSpringBoot.Respository.ConfigScoreRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class ConfigControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ConfigScoreDetailRepository configScoreDetailRepository;
	
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
		        .param("exercise", "10")
		        .param("practice", "20")
		        .param("test", "20")
		        .param("examFinal", "40")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 10);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 10);
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
	public void testSaveConfigNullAttendace() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "")
		        .param("exercise", "20")
		        .param("practice", "20")
		        .param("test", "20")
		        .param("examFinal", "40")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
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
	public void testSaveConfigNullExercise() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "20")
		        .param("exercise", "")
		        .param("practice", "20")
		        .param("test", "20")
		        .param("examFinal", "40")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
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
	public void testSaveConfigNullPractice() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "20")
		        .param("exercise", "20")
		        .param("practice", "")
		        .param("test", "20")
		        .param("examFinal", "40")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
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
	}
	
	@Test
	@Transactional
	public void testSaveConfigNullTest() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "20")
		        .param("exercise", "20")
		        .param("practice", "20")
		        .param("test", "")
		        .param("examFinal", "40")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
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
	public void testSaveConfigNullExamFinal() throws Exception {
		String id = "1";
		MockHttpServletRequestBuilder createMessage = post("/saveConfig")
		        .param("attendance", "20")
		        .param("exercise", "40")
		        .param("practice", "20")
		        .param("test", "20")
		        .param("examFinal", "")
		        .param("idSubject", id);
		
		mockMvc.perform(createMessage)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/subjectConfig/" + id));
		
		List<ConfigScoreDetail> configScoreDetails = configScoreDetailRepository.findByConfigScore_SubjectId((long)1);
		for(ConfigScoreDetail configScoreDetail : configScoreDetails) {
			if(configScoreDetail.getName().equals("attendance"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("exercise"))
				assertEquals(configScoreDetail.getPercent(), 40);
			if(configScoreDetail.getName().equals("practice"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("test"))
				assertEquals(configScoreDetail.getPercent(), 20);
			if(configScoreDetail.getName().equals("examFinal"))
				assertFalse(true);
		}
	}
	
	
}
