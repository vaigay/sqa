package com.vaigay.WebSpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaigay.WebSpringBoot.Service.ServiceScoreController;

@Controller
public class ScoreController {
	
	@Autowired
	private ServiceScoreController ser;
	
	@RequestMapping("/viewScore")
	public String getViewScore(Model model) {
		model.addAttribute("listSemester", ser.getAllSemester());
		return "viewScore";
	}
	
	@RequestMapping("/ViewBySemester")
	public String getViewSubClass(@RequestParam("semester") long ids,Model model) {
		model.addAttribute("listSubject",ser.getSubjectBySemester(ids));
		return "classSemester";
	}
}
