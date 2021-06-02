package com.vaigay.WebSpringBoot.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRepository;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Respository.UserRepository;

@Service
public class ServiceUser {
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
	
	
	public void saveUser(User user) {
		userRepository.save(user);
		updateCode(user);
	}

	
	public void updateCode(User user) {
		String c = courseRepository.findNameCourseByID(user.getCourse().getId());
		String course = c.replaceFirst("D","B");
		StringBuilder b = new StringBuilder();
		b.append(course);
		String shortName = majorRepository.findShortNameMajorByID(user.getMajor().getId());
		b.append(shortName);
		b.append(user.getId());
		user.setCode(b.toString());
		userRepository.updateUserCode(user.getId(), user.getCode());
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
	
}
