package com.vaigay.WebSpringBoot;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testViewReportPage() throws Exception {
		mockMvc.perform(get("/report"))
		.andExpect(status().isOk())
		.andExpect(view().name("reportDetail"))
		.andExpect(model().attributeExists("listCourse","listMajor"));
	}
	
	@Test
	public void testReportUserByFieldAll() throws Exception{
		mockMvc.perform(get("/reportBy").param("CourseId", "0").param("MajorId", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("reportDetail"))
		.andExpect(model().attributeExists("listUser", "listMess","listCourse","listMajor"));
	}
	
	@Test
	public void testReportUserByFieldWithOneMajorAndAllCourse() throws Exception{
		mockMvc.perform(get("/reportBy").param("CourseId", "0").param("MajorId", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("reportDetail"))
		.andExpect(model().attributeExists("listUser", "listMess","listCourse","listMajor"));
	}
	
	@Test
	public void testReportUserByFieldWithAllMajorAndOneCourse() throws Exception{
		mockMvc.perform(get("/reportBy").param("CourseId", "1").param("MajorId", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("reportDetail"))
		.andExpect(model().attributeExists("listUser", "listMess","listCourse","listMajor"));
	}
	
	@Test
	public void testReportUserByFieldWithOneMajorAndOneCourse() throws Exception{
		mockMvc.perform(get("/reportBy").param("CourseId", "1").param("MajorId", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("reportDetail"))
		.andExpect(model().attributeExists("listUser", "listMess","listCourse","listMajor"));
	}
	
	@Test
	public void testReportUserByFieldWithInvalidCourse() throws Exception{
		try {
			mockMvc.perform(get("/reportBy").param("CourseId", "-1").param("MajorId", "1"))
			.andExpect(status().is5xxServerError())
			.andExpect(view().name("reportDetail"));
		}catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testReportUserByFieldWithInvalidMajor(){
		try {
			mockMvc.perform(get("/reportBy").param("CourseId", "0").param("MajorId", "-1"))
			.andExpect(status().is5xxServerError())
			.andExpect(view().name("reportDetail"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
