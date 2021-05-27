package com.vaigay.WebSpringBoot.Controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaigay.WebSpringBoot.Service.ServiceConfig;

@Controller
public class ConfigController {
	
	@Autowired
	private ServiceConfig service;

	
	@RequestMapping("/config")
	public String viewSubject(Model model) {
		model.addAttribute("listSubject", service.getSubjectConfig());
		return "config";
	}
	
	@RequestMapping("/subjectConfig/{id}")
	public String viewConfigSubject(@PathVariable(name = "id") long id,Model model) {
		service.setModelScoreConfig(id, model);
		model.addAttribute("idSubject", id);
		model.addAttribute("subject",service.getSubjectByid(id));
		return "configDetail";
	}
	
	@RequestMapping(value = "/saveConfig" , method = RequestMethod.POST)
	public String saveConfigSubject(RedirectAttributes reAttributes, String attendance,String exercise,String practice,String examFinal,String test, String idSubject) {
		reAttributes.addFlashAttribute("mess", "Cấu hình thành công");
		service.saveConfig(attendance, exercise, practice, examFinal, test, idSubject);
		return "redirect:/subjectConfig/" + idSubject;
	}
	
}
