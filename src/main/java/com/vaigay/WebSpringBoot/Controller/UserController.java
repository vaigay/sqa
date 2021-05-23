package com.vaigay.WebSpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Service.ServiceUser;

@Controller
public class UserController {
	@Autowired
	private ServiceUser service;
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}
	
//	@RequestMapping("/test")
//	public String test(@RequestParam("name") String name) {
//		service.test(name);
//		return "index";
//	}
	
	@RequestMapping("/listUser")
	public String listUserPage(Model model) {
		model.addAttribute("listUser", service.getAllUser());
		return "listUser";
	}
	
	@RequestMapping("/newUser")
	public String getNewUser(Model model) {
		model.addAttribute("listCourse", service.listAllCourse());
		model.addAttribute("listMajor",service.listAllMajor());
		model.addAttribute("user", new User());
		return "newUser";
	}
	
	@RequestMapping(value = "/user/saveNew",method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,RedirectAttributes reAttributes) {
		user.setStatus(1);
		reAttributes.addFlashAttribute("mess", "Lưu thành công!");
		service.saveUser(user);
		return "redirect:/listUser";
	}
	
	@RequestMapping(value = "/saveEditUser",method = RequestMethod.POST)
	public String saveEditUser(@ModelAttribute("user") User user, RedirectAttributes reAttributes) {
		user.setStatus(1);
		service.saveUser(user);
		reAttributes.addFlashAttribute("mess", "Sửa thành công!");
		return "redirect:/listUser";
	}
	
	@RequestMapping("/findUserByCode")
	public String getListUser(@RequestParam("code") String code, Model model) {
		model.addAttribute("listUser", service.getListUserByCode(code));
		return "listUser";
	}
	
	@RequestMapping("/user/edit/{id}")
	public String editUser(@PathVariable(name = "id") long id,Model model) {
		model.addAttribute("user",service.getUserById(id));
		model.addAttribute("listCourse", service.listAllCourse());
		model.addAttribute("listMajor",service.listAllMajor());
		return "editUser";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") long id) {
		System.out.println("delete");
		service.deleteUser(id);
		return "redirect:/listUser";
	}
	
}
