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
	private float attendance;
	@Column(nullable = true)
	private float test;
	@Column(nullable = true)
	private float practice;
	@Column(nullable = true)
	private float exercise;
	@Column(nullable = true)
	private float examFinal;
	
	
	private float finalScore;
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
	public float getAttendance() {
		return attendance;
	}
	public void setAttendance(float attendance) {
		this.attendance = attendance;
	}
	public float getTest() {
		return test;
	}
	public void setTest(float test) {
		this.test = test;
	}
	public float getPractice() {
		return practice;
	}
	public void setPractice(float practice) {
		this.practice = practice;
	}
	public float getExercise() {
		return exercise;
	}
	public void setExercise(float exercise) {
		this.exercise = exercise;
	}
	public float getExamFinal() {
		return examFinal;
	}
	public void setExamFinal(float examFinal) {
		this.examFinal = examFinal;
	}
	public float getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}
	public String getByWord() {
		return byWord;
	}
	public void setByWord(String byWord) {
		this.byWord = byWord;
	}
	
	
	
	
}
