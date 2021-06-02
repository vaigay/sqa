package com.vaigay.WebSpringBoot.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class User {
	public User(String fullName, Date dateOfBirth, Course course, String address, String email,
			Major major, int status) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.course = course;
		this.address = address;
		this.email = email;
		this.major = major;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(nullable = false)
	private String fullName;
	
	private String code;
	@Column(nullable = false)
	private Date dateOfBirth;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id",nullable = false)
	private Course course;
	
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "major_id",nullable = false)
	private Major major;
	private int status;

	public long getId() {
		return id;
	}

	public User(long id, String fullName, String code, Date dateOfBirth, Course course, String address, String email,
			Major major, int status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.code = code;
		this.dateOfBirth = dateOfBirth;
		this.course = course;
		this.address = address;
		this.email = email;
		this.major = major;
		this.status = status;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", code=" + code + ", dateOfBirth=" + dateOfBirth
				 + ", address=" + address + ", email=" + email + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
