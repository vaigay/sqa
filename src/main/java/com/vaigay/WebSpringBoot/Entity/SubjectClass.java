package com.vaigay.WebSpringBoot.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class SubjectClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "semester_id")
	private Semester semester;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectClass")
	private List<UserInClass> userInClass;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="configScore_id")
	private ConfigScore configScore;
	
	private String teacher;
	private int credits;
	private String code;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public List<UserInClass> getUserInClass() {
		return userInClass;
	}
	public void setUserInClass(List<UserInClass> userInClass) {
		this.userInClass = userInClass;
	}
	public ConfigScore getConfigScore() {
		return configScore;
	}
	public void setConfigScore(ConfigScore configScore) {
		this.configScore = configScore;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
