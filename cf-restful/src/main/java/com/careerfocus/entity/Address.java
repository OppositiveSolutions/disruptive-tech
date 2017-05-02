package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name="address")
public class Address {
	@Id
	@Column(name="address_id", columnDefinition="INT")
	private int addressId;

	@Basic
	private String city;

	@Basic
	@Column(name="land_mark")
	private String landMark;

	@Basic
	@Column(name="pin_code", columnDefinition="INT")
	private int pinCode;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="stateId", scope=States.class)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="state_id", columnDefinition="INT")
	private States state;

	@Basic
	@Column(name="street_address", length=2000)
	private String streetAddress;

	@JsonIgnore
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId", scope=User.class)
	@ManyToMany(targetEntity=User.class, cascade=CascadeType.MERGE)
	@JoinTable(name="user_address", joinColumns=@JoinColumn(name="address_id", columnDefinition="INT", nullable=false), inverseJoinColumns=@JoinColumn(name="user_id", columnDefinition="INT"))
	private Set<User> users = new HashSet<User>();


	public Address() {
	}

	public Address(int addressId) {
		this.addressId = addressId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public States getStates() {
		return state;
	}

	public void setStates(States states) {
		this.state = states;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}