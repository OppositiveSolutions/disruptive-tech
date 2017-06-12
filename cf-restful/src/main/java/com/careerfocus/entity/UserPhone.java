package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "user_phone")
public class UserPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_phone_id", columnDefinition = "INT")
    private int userPhoneId;

    @Basic
    @Column(name = "phone_no", nullable = false, length = 45)
    private String phoneNo;

    @Basic
    @Column(columnDefinition = "INT")
    private int type;

    @Basic
    @Column(name = "is_primary")
    private boolean isPrimary;

    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = User.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", columnDefinition = "INT", nullable = false)
    private User user;

    public UserPhone() {
    }

    public UserPhone(String phoneNo, int type, boolean isPrimary) {
        this.phoneNo = phoneNo;
        this.type = type;
        this.isPrimary = isPrimary;
    }

    public UserPhone(int userPhoneId) {
        this.userPhoneId = userPhoneId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserPhoneId() {
        return userPhoneId;
    }

    public void setUserPhoneId(int userPhoneId) {
        this.userPhoneId = userPhoneId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

}