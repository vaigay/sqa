package com.vaigay.WebSpringBoot.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private List<SubjectClass> subjectClass;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="configScore_id")
	private ConfigScore configScore;
	private String name;
	private String code;
	private int credits;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public List<SubjectClass> getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(List<SubjectClass> subjectClass) {
		this.subjectClass = subjectClass;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ConfigScore getConfigScore() {
		return configScore;
	}
	public void setConfigScore(ConfigScore configScore) {
		this.configScore = configScore;
	}
}
