package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaigay.WebSpringBoot.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByCodeContainingAndStatus(String code,int status);
	
	int countByMajorIdAndStatus(long id,int status);
	
	int countByCourseIdAndStatus(long id,int status);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.status = :status WHERE u.id = :id")
	void updateStatus(@Param("id") long id, @Param("status") int status);
	
	@Query("SELECT u.course.name, count(u.course.name) FROM User u WHERE u.major.id = :id AND u.status = :status GROUP BY u.course.name")
	List<Object[]> countAllUserAndCourseByMajorAndStatus(@Param("id") long id,@Param("status") int status);
	
	@Query("SELECT u.major.nameMajor, count(u.major.nameMajor) FROM User u WHERE u.course.id = :id AND u.status = :status GROUP BY u.major.nameMajor")
	List<Object[]> countAllUserAndMajorByCourseAndStatus(@Param("id") long id, @Param("status") int status);
	
	List<User> findByMajorIdAndStatus(long idMajor,int status);
	
	List<User> findByCourseIdAndStatus(long idCourse,int status);

	List<User> findByMajorIdAndCourseIdAndStatus(long idMajor,long idCourse,int status);
	
	int	countByMajorIdAndCourseIdAndStatus(long idMajor,long idCourse,int status);
	
	List<User> findByStatus(int status);
	
	User findTopByOrderByIdDesc();
	
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.code = :code WHERE u.id = :id")
	void updateUserCode(@Param("id") long id, @Param("code") String code);
	
	
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.status = :status")
	int countWithStatus(@Param("status") int status);
}
