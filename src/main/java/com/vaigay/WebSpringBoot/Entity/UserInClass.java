package com.vaigay.WebSpringBoot.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserInClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectClass_id")
	private SubjectClass subjectClass;
	
	@OneToOne(mappedBy = "userInClass")
	private ResultOfUser resultOfUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubjectClass getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(SubjectClass subjectClass) {
		this.subjectClass = subjectClass;
	}

	public ResultOfUser getResultOfUser() {
		return resultOfUser;
	}

	public void setResultOfUser(ResultOfUser resultOfUser) {
		this.resultOfUser = resultOfUser;
	}
}
