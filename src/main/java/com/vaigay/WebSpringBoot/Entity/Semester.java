package com.vaigay.WebSpringBoot.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "semester")
	private List<SubjectClass> subjectClass;
	
	private String name;

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

	public List<SubjectClass> getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(List<SubjectClass> subjectClass) {
		this.subjectClass = subjectClass;
	}

	
	
}
