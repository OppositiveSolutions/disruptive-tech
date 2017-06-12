package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "center")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "center_id", columnDefinition = "INT")
    private int centerId;

    @Basic
    @Column(name = "center_code", nullable = false, length = 45)
    private String centerCode;

    @Basic
    @Column(length = 45)
    private String place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", columnDefinition = "INT")
    private Address address;

    @Basic
    @Column(name = "center_latitude", length = 45)
    private String centerLatitude;

    @Basic
    @Column(name = "center_longitude", length = 45)
    private String centerLongitude;

    @Basic
    @Column(name = "is_franchise")
    private boolean isFranchise;

    @JsonIgnore
    @OneToMany(targetEntity = Student.class, mappedBy = "center", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<Student>();

    public Center() {
    }

    public Center(int centerId) {
        this.centerId = centerId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterLatitude() {
        return centerLatitude;
    }

    public void setCenterLatitude(String centerLatitude) {
        this.centerLatitude = centerLatitude;
    }

    public String getCenterLongitude() {
        return centerLongitude;
    }

    public void setCenterLongitude(String centerLongitude) {
        this.centerLongitude = centerLongitude;
    }

    public boolean isIsFranchise() {
        return isFranchise;
    }

    public void setIsFranchise(boolean isFranchise) {
        this.isFranchise = isFranchise;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}