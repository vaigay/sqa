package com.vaigay.WebSpringBoot.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ResultOfUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name ="userInClass_id")
	private UserInClass userInClass;
	
	@Column(nullable = true)
	private Float attendance;
	@Column(nullable = true)
	private Float test;
	@Column(nullable = true)
	private Float practice;
	@Column(nullable = true)
	private Float exercise;
	@Column(nullable = true)
	private Float examFinal;
	
	
	private Float finalScore;
	private String byWord;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserInClass getUserInClass() {
		return userInClass;
	}
	public void setUserInClass(UserInClass userInClass) {
		this.userInClass = userInClass;
	}
	public Float getAttendance() {
		return attendance;
	}
	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}
	public Float getTest() {
		return test;
	}
	public void setTest(Float test) {
		this.test = test;
	}
	public Float getPractice() {
		return practice;
	}
	public void setPractice(Float practice) {
		this.practice = practice;
	}
	public Float getExercise() {
		return exercise;
	}
	public void setExercise(Float exercise) {
		this.exercise = exercise;
	}
	public Float getExamFinal() {
		return examFinal;
	}
	public void setExamFinal(Float examFinal) {
		this.examFinal = examFinal;
	}
	public Float getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(Float finalScore) {
		this.finalScore = finalScore;
	}
	public String getByWord() {
		return byWord;
	}
	public void setByWord(String byWord) {
		this.byWord = byWord;
	}
	
	
	
	
}
