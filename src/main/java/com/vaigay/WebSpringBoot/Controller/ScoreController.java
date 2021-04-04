package com.vaigay.WebSpringBoot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.SubjectClass;
import com.vaigay.WebSpringBoot.Entity.UserInClass;
import com.vaigay.WebSpringBoot.Service.ServiceScore;

@Controller
public class ScoreController {
	
	@Autowired
	private ServiceScore ser;
	
	
	//Xem các học kỳ
	@RequestMapping("/viewScore")
	public String getViewScore(Model model) {
		model.addAttribute("listSemester", ser.getAllSemester());
		return "viewScore";
	}
	
	//Xem các Môn học trong 1 học kỳ
	@RequestMapping("/ViewBySemester")
	public String getViewSubClass(@RequestParam("semester") long ids,Model model) {
		model.addAttribute("listSubject",ser.getSubjectBySemester(ids));
		model.addAttribute("semester",ids);
		return "classSemester";
	}
	
	
	//Xem danh sách các Lớp học trong 1 học kỳ
	@RequestMapping("/subjectClass/{idc}/semester/{ids}")
	public String getClassSubjectView(@PathVariable(name = "idc") long id,Model model,@PathVariable(name = "ids") long ids) {
		model.addAttribute("listSubjectClass", ser.getSubjectClassInSemester(id,ids));
		model.addAttribute("subject", ser.getNameSubject(id));
		return "viewSubjectClass";
	}
	
	
	//Xem Chi tiết điểm của 1 lớp học
	@RequestMapping("/ScoreDetail/{id}")
	public String viewScoreDetail(@PathVariable(name = "id") long id,Model model) {
//		System.out.println(id);
		SubjectClass subjectClass = ser.getOneSubjectClass(id);
		List<UserInClass> listUser = subjectClass.getUserInClass();
		ConfigScore configScore = subjectClass.getConfigScore();
		List<String> Header = ser.getHeaderTableScore(configScore);
		model.addAttribute("listTableHeader",Header);
		model.addAttribute("listUserInClass",listUser);
		return "scoreClassDetail";
	}
	
}
