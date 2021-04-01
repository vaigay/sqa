package com.vaigay.WebSpringBoot.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaigay.WebSpringBoot.Entity.Course;

public interface CourseRepository  extends JpaRepository<Course, Long>{
	
	@Query("SELECT course.name FROM Course course WHERE course.id = :id")
	String findNameCourseByID(@Param("id") long id);
}
