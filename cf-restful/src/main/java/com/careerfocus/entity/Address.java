package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", columnDefinition = "INT")
    private int addressId;
    
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = User.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", columnDefinition = "INT", nullable = false)
    private User user;

    @Basic
    @Column(name = "street_address", length = 2000)
    private String streetAddress;

    @Basic
    @Column(name = "land_mark")
    private String landMark;

    @Basic
    private String city;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "stateId", scope = States.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", columnDefinition = "INT")
    private States state;

    @Basic
    @Column(name = "pin_code", columnDefinition = "INT")
    private int pinCode;

    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = User.class)
    @ManyToMany(targetEntity = User.class, cascade = CascadeType.MERGE, mappedBy = "address")
//	@JoinTable(name="user_address", joinColumns=@JoinColumn(name="address_id", columnDefinition="INT", nullable=false), inverseJoinColumns=@JoinColumn(name="user_id", columnDefinition="INT"))
    private Set<User> users = new HashSet<User>();


    public Address() {
    }

    public Address(String streetAddress, String landMark, String city, States state, int pinCode, int user) {
        this.streetAddress = streetAddress;
        this.landMark = landMark;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.user = new User(user);
    }

    public Address(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {
        return addressId;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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