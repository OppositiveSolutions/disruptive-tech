//package com.careerfocus.entity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
////@Entity
////@Table(name = "student")
//public class Temp0 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "student_id", unique = true, nullable = false)
//	int studentId;
//
//	@Column(name = "name")
//	String name;
//
//	@Column(name = "rank")
//	int rank;
//
//	@JsonIgnore
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "student_id")
//	Temp address;
//
//	public Temp getAddress() {
//		return address;
//	}
//
//	public void setAddress(Temp address) {
//		this.address = address;
//		this.address.setStudent(this);
//	}
//
//	public Temp0() {
//
//	}
//
//	public Temp0(int id, String name, int rank) {
//		super();
//		this.studentId = id;
//		this.name = name;
//		this.rank = rank;
//	}
//
//	public int getId() {
//		return studentId;
//	}
//
//	public void setId(int id) {
//		this.studentId = id;
//	}
//
//	public String getDirection() {
//		return name;
//	}
//
//	public void setDirection(String name) {
//		this.name = name;
//	}
//
//	public int getRank() {
//		return rank;
//	}
//
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//
//}
