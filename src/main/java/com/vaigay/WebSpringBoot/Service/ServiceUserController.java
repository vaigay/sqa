package com.vaigay.WebSpringBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRespository;
import com.vaigay.WebSpringBoot.Respository.MajorRespository;
import com.vaigay.WebSpringBoot.Respository.UserRespository;

@Service
public class ServiceUserController {
	@Autowired
	private CourseRespository courseRespository;
	
	@Autowired
	private MajorRespository majorRespository;
	
	@Autowired
	private UserRespository userRespository;
	
	public List<Course> listAllCourse(){
		return courseRespository.findAll();
	}
	
	public List<Major> listAllMajor(){
		return majorRespository.findAll();
	}
	
	public void updateUser(User user) {
		String c = courseRespository.findNameCourseByID(user.getCourse().getId());
		String course = c.replaceFirst("D","B");
		StringBuilder b = new StringBuilder();
		b.append(course);
		//System.out.println(user.getMajor().getId());
		String shortName = majorRespository.findShortNameMajorByID(user.getMajor().getId());
		//System.out.println(shortName);
		b.append(shortName);
		b.append(user.getId());
		user.setCode(b.toString());
		userRespository.save(user);
		System.out.println(user);
	}
	
	public void saveUser(User user) {
		userRespository.save(user);
		updateUser(user);
	}
	
	public List<User> getListUserByCode(String code){
		return userRespository.findByCodeContaining(code);
	}
	
	public User getUserById(long id) {
		return userRespository.getOne(id);
	}
	
	public void deleteUser(long id) {
		userRespository.deleteById(id);
	}
	
	public List<User> getAllUser(){
		return userRespository.findAll();
	}
	
//	public void test(String name) {
//		Course c = new Course();
//		c.setName(name);
//		courseRespository.save(c);
//	}
}
