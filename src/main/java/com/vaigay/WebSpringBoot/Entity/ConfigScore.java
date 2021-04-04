package com.vaigay.WebSpringBoot.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ConfigScore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "configScore",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ConfigScoreDetail> listConfig;
	
	@OneToOne(mappedBy = "configScore")
	private Subject subject;
	
	@OneToMany(mappedBy = "configScore",fetch = FetchType.LAZY)
	private List<SubjectClass> subjectClass;;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<ConfigScoreDetail> getListConfig() {
		return listConfig;
	}
	public void setListConfig(List<ConfigScoreDetail> listConfig) {
		this.listConfig = listConfig;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public List<SubjectClass> getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(List<SubjectClass> subjectClass) {
		this.subjectClass = subjectClass;
	}
	
}
