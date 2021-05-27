package com.vaigay.WebSpringBoot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.vaigay.WebSpringBoot.Respository.SemesterRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoreControllerTest {
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetViewScore() throws Exception {
		int size = (int) semesterRepository.count();
		
		mockMvc.perform(get("/viewScore"))
		.andExpect(status().isOk())
		.andExpect(view().name("viewScore"))
		.andExpect(model().attribute("listSemester", hasSize(size)));
	}
	
	@Test
	public void testGetViewSubClass() throws Exception{
		
		String id = "1";
		mockMvc.perform(get("/ViewBySemester").param("semester", id))
		.andExpect(status().isOk())
		.andExpect(view().name("classSemester"))
		.andExpect(model().attributeExists("listSubject"))
		.andExpect(model().attribute("semester", (long)1));
	}
	
	@Test
	public void testGetViewSubClassNoClass() throws Exception{
		
		String id = "2";
		mockMvc.perform(get("/ViewBySemester").param("semester", id))
		.andExpect(status().isOk())
		.andExpect(view().name("classSemester"))
		.andExpect(model().attribute("listSubject", hasSize(0)))
		.andExpect(model().attribute("semester", (long)2));
	}
	
	@Test
	public void testGetClassSubjectView() throws Exception{
		mockMvc.perform(get("/subjectClass/1/semester/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("viewSubjectClass"))
		.andExpect(model().attributeExists("listSubjectClass","subject"));
	}
	
	@Test
	public void testGetClassSubjectViewWithSubjectHasNoClass() throws Exception{
		mockMvc.perform(get("/subjectClass/3/semester/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("viewSubjectClass"))
		.andExpect(model().attributeExists("subject"))
		.andExpect(model().attribute("listSubjectClass", hasSize(0)));
	}
	
	@Test
	public void testGetClassSubjectViewWithNoSubject() throws Exception{
		mockMvc.perform(get("/subjectClass/80/semester/1"))
		.andExpect(status().is4xxClientError());
		
	}
	
	@Test
	public void testViewScoreDetail() throws Exception{
		mockMvc.perform(get("/ScoreDetail/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("scoreClassDetail"))
		.andExpect(model().attributeExists("listTableHeader","listUserInClass"));
	}
	
	@Test
	public void testViewScoreDetailWithClassDoNotHaveUser() throws Exception{
		mockMvc.perform(get("/ScoreDetail/2"))
		.andExpect(status().isOk())
		.andExpect(view().name("scoreClassDetail"))
		.andExpect(model().attributeExists("listTableHeader"))
		.andExpect(model().attribute("listUserInClass", hasSize(0)));
	}
	
	@Test
	public void testViewScoreDetailWithInvalidClass() throws Exception{
		try {
			mockMvc.perform(get("/ScoreDetail/5"))
			.andExpect(status().is4xxClientError());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
