package com.careerfocus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "address")
public class Temp {
	
	@Id
	@GeneratedValue(generator = "newGenerator")
	@GenericGenerator(name = "newGenerator", strategy = "foreign", parameters = { @Parameter(value = "student", name = "property")})
	@Column(name = "student_id")
	int studentId;
	
	String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	Temp0 student;
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Temp0 getStudent() {
		return student;
	}

	public void setStudent(Temp0 student) {
		this.student = student;
	}

}
