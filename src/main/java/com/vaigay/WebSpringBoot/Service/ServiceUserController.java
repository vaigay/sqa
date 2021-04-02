package com.vaigay.WebSpringBoot.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRepository;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Respository.UserRepository;

@Service
public class ServiceUserController {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private MajorRepository majorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Course> listAllCourse(){
		return courseRepository.findAll();
	}
	
	public List<Major> listAllMajor(){
		return majorRepository.findAll();
	}
	
	public void updateUser(User user) {
		String c = courseRepository.findNameCourseByID(user.getCourse().getId());
		String course = c.replaceFirst("D","B");
		StringBuilder b = new StringBuilder();
		b.append(course);
		//System.out.println(user.getMajor().getId());
		String shortName = majorRepository.findShortNameMajorByID(user.getMajor().getId());
		//System.out.println(shortName);
		b.append(shortName);
		b.append(user.getId());
		userRepository.updateCodeUser(user.getId(), b.toString());
		System.out.println(user);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
		updateUser(user);
	}
	
	public List<User> getListUserByCode(String code){
		return userRepository.findByCodeContainingAndStatus(code,1);
	}
	
	public User getUserById(long id) {
		return userRepository.getOne(id);
	}
	
	public void deleteUser(long id) {
		userRepository.updateStatus(id,(int)0);
	}
	
	public List<User> getAllUser(){
		return userRepository.findByStatus((int)1);
	}
	
//	public void test(String name) {
//		Course c = new Course();
//		c.setName(name);
//		courseRepository.save(c);
//	}
}
