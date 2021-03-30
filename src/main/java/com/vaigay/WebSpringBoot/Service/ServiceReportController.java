package com.vaigay.WebSpringBoot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.CourseRespository;
import com.vaigay.WebSpringBoot.Respository.MajorRespository;
import com.vaigay.WebSpringBoot.Respository.UserRespository;

@Component
public class ServiceReportController {
	@Autowired
	private CourseRespository courseRespository;
	
	@Autowired
	private MajorRespository majorRespository;
	
	@Autowired
	private UserRespository userRespository;
	
	public List<User> getAllUser(){
		return userRespository.findAll();
	}
	
	public List<Major> getAllMajor(){
		return majorRespository.findAll();
	}
	
	public List<Course> getAllCourses(){
		return courseRespository.findAll();
	}
	
	public List<String> getReportAllMess(){
		List<String> listMess = new ArrayList<String>();
		listMess.add("Báo cáo tổng");
		String countAll = "Tổng số sinh viên: " + userRespository.count();
		listMess.add(countAll);
		List<Course> listCourses =  courseRespository.findAll();
		for(Course tmp : listCourses) {
			String countCourse = "Sinh viên khoá " + tmp.getName() + ": " + userRespository.countByCourseId(tmp.getId());
			listMess.add(countCourse);
		}
		List<Major> listMajors = majorRespository.findAll();
		for(Major tmp : listMajors) {
			String countMajor = "Sinh viên ngành " + tmp.getNameMajor() + ": " + userRespository.countByCourseId(tmp.getId());
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
		String add = "Số sinh viên của khoá " + courseRespository.getOne(courseId).getName();
		add = add + " Chuyên ngành " + majorRespository.getOne(majorId).getNameMajor() +": ";
		add = add + userRespository.countByMajorIdAndCourseId(majorId, courseId);
		listMess.add(add);
		return listMess;
	}

	public List<String> getMessCourse(long courseId) {
		List<String> listMess = new ArrayList<String>();
		listMess.add("Báo cáo số sinh viên của khoá " + courseRespository.getOne(courseId).getName() + ": " + userRespository.countByCourseId(courseId));
		List<Object[]> result = userRespository.countAllUserAndMajorByCourse(courseId);
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
		listMess.add("Báo cáo số sinh viên của ngành " + majorRespository.getOne(majorId).getNameMajor() + ": " + userRespository.countByMajorId(majorId));
		List<Object[]> result = userRespository.countAllUserAndCourseByMajor(majorId);
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
			return userRespository.findByMajorIdAndCourseId(majorId,courseId);
		if(courseId != 0)
			return userRespository.findByCourseId(courseId);
		if(majorId != 0)
			return userRespository.findByMajorId(majorId);
		return userRespository.findAll();
	}
}
