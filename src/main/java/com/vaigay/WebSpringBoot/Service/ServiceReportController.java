package com.vaigay.WebSpringBoot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRepository;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Respository.UserRepository;

@Component
public class ServiceReportController {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private MajorRepository majorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public List<Major> getAllMajor(){
		return majorRepository.findAll();
	}
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public List<String> getReportAllMess(){
		List<String> listMess = new ArrayList<String>();
		listMess.add("Báo cáo tổng");
		String countAll = "Tổng số sinh viên: " + userRepository.count();
		listMess.add(countAll);
		List<Course> listCourses =  courseRepository.findAll();
		for(Course tmp : listCourses) {
			String countCourse = "Sinh viên khoá " + tmp.getName() + ": " + userRepository.countByCourseId(tmp.getId());
			listMess.add(countCourse);
		}
		List<Major> listMajors = majorRepository.findAll();
		for(Major tmp : listMajors) {
			String countMajor = "Sinh viên ngành " + tmp.getNameMajor() + ": " + userRepository.countByMajorId(tmp.getId());
			listMess.add(countMajor);
		}
		return listMess;
	}
	
	public List<String> getReportMessByField(long courseId, long majorId){
		List<String> listMess = new ArrayList<String>();
		if(courseId == 0 && majorId == 0)
			return getReportAllMess();
		else if(courseId == 0)
			listMess = getMessMajor(majorId);
		else if(majorId == 0)
			listMess = getMessCourse(courseId);
		else
			listMess = getMessCourseAndMajor(courseId,majorId);
		return listMess;
	}

	private List<String> getMessCourseAndMajor(long courseId, long majorId) {
		List<String> listMess = new ArrayList<String>();
		String add = "Số sinh viên của khoá " + courseRepository.getOne(courseId).getName();
		add = add + " Chuyên ngành " + majorRepository.getOne(majorId).getNameMajor() +": ";
		add = add + userRepository.countByMajorIdAndCourseId(majorId, courseId);
		listMess.add(add);
		return listMess;
	}

	public List<String> getMessCourse(long courseId) {
		List<String> listMess = new ArrayList<String>();
		listMess.add("Báo cáo số sinh viên của khoá " + courseRepository.getOne(courseId).getName() + ": " + userRepository.countByCourseId(courseId));
		List<Object[]> result = userRepository.countAllUserAndMajorByCourse(courseId);
		for(Object[] tmp : result) {
			int quantity = Integer.parseInt(tmp[1].toString());
			if(quantity > 0) {
				String count = "Sinh viên ngành " + tmp[0].toString() + ": " + quantity;
				listMess.add(count);
			}
		}
		return listMess;
	}

	public List<String> getMessMajor(long majorId) {
		List<String> listMess = new ArrayList<String>();
		listMess.add("Báo cáo số sinh viên của ngành " + majorRepository.getOne(majorId).getNameMajor() + ": " + userRepository.countByMajorId(majorId));
		List<Object[]> result = userRepository.countAllUserAndCourseByMajor(majorId);
		for(Object[] tmp : result) {
			int quantity = Integer.parseInt(tmp[1].toString());
			if(quantity > 0) {
				String count = "Sinh viên khoá " + tmp[0].toString() + ": " + quantity;
				listMess.add(count);
			}
		}
		return listMess;
	}
	
	public List<User> getUserByField(long courseId, long majorId){
		if(courseId != 0 && majorId != 0) 
			return userRepository.findByMajorIdAndCourseId(majorId,courseId);
		if(courseId != 0)
			return userRepository.findByCourseId(courseId);
		if(majorId != 0)
			return userRepository.findByMajorId(majorId);
		return userRepository.findAll();
	}
}
