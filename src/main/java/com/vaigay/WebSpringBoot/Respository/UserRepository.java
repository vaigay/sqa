package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaigay.WebSpringBoot.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByCodeContaining(String code);
	
	@Query("SELECT count(u) FROM User u WHERE u.major.id = :id")
	int countByMajorId(@Param("id") long id);
	
	@Query("SELECT count(u) FROM User u WHERE u.course.id = :id")
	int countByCourseId(@Param("id") long id);
	
//	@Query("SELECT count(u) FROM User u WHERE u.major.id = :id1 AND u.course.id = :id2")
//	int countUserInMajorAndCourse(@Param("id1") long idMajor,@Param("id2") long idCourse);
	
	@Query("SELECT DISTINCT u.course.name, count(u) FROM User u WHERE u.major.id = :id ")
	List<Object[]> countAllUserAndCourseByMajor(@Param("id") long id);
	
	@Query("SELECT DISTINCT u.major.nameMajor, count(u) FROM User u WHERE u.course.id = :id ")
	List<Object[]> countAllUserAndMajorByCourse(@Param("id") long id);
	
	List<User> findByMajorId(long idMajor);
	
	List<User> findByCourseId(long idCourse);

	List<User> findByMajorIdAndCourseId(long idMajor,long idCourse);
	
	int	countByMajorIdAndCourseId(long idMajor,long idCourse);
}
