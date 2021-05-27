package com.vaigay.WebSpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaigay.WebSpringBoot.Service.ServiceReport;

@Controller
public class ReportController {
	
	@Autowired
	private ServiceReport service;
	
	@RequestMapping("/report")
	public String viewReportPage(Model model) {
		model.addAttribute("listCourse", service.getAllCourses());
		model.addAttribute("listMajor", service.getAllMajor());
		return "reportDetail";
	}
	
	
	@RequestMapping("/reportBy")
	public String reportUserByField(@RequestParam("CourseId") String CourseId,@RequestParam("MajorId") String MajorId,Model model) {
		long cid = Long.parseLong(CourseId);
		long mid = Long.parseLong(MajorId);
		model.addAttribute("listUser", service.getUserByField(cid, mid));
		model.addAttribute("listMess", service.getReportMessByField(cid, mid));
		model.addAttribute("listCourse", service.getAllCourses());
		model.addAttribute("listMajor", service.getAllMajor());
		return "reportDetail";
	}
}
