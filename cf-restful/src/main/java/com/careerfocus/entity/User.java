package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = "INT")
    private int userId;

    @Basic
    @Column(nullable = false, length = 45)
//	@Convert(converter = JPACryptoConverter.class)
    private String username;

    @Basic
    @Column(nullable = false, length = 45)
//	@Convert(converter = JPACryptoConverter.class)
    @JsonIgnore
    private String password;

    @Basic
    @Column(name = "created_date", nullable = false, insertable = false, updatable = false)
    private Date createdDate;

    @Basic
    @Column(columnDefinition = "INT")
    private int role;

    @Basic
    @Column(name = "first_name", nullable = false, length = 46)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 46)
    private String lastName;

    @Basic
    @Column(length = 11)
    private String gender;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dob;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId", scope = Address.class)
    @ManyToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id", columnDefinition = "INT", nullable = false), inverseJoinColumns = @JoinColumn(name = "address_id", columnDefinition = "INT"))
    private Set<Address> address = new HashSet<Address>();

    @OneToMany(targetEntity = UserPhone.class, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserPhone> userPhones = new HashSet<UserPhone>();

    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = Student.class)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

//	@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.MERGE)
//	private UserProfilePic userProfilePic;

    public User() {
    }

    public User(String username, String password, int role, String firstName, String lastName, String gender, Date dob, Date createdDate) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.createdDate = createdDate;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<UserPhone> getUserPhones() {
        return userPhones;
    }

    public void setUserPhones(Set<UserPhone> userPhones) {
        this.userPhones = userPhones;
    }

//	public UserProfilePic getUserProfilePic() {
//		return userProfilePic;
//	}
//
//	public void setUserProfilePic(UserProfilePic userProfilePic) {
//		this.userProfilePic = userProfilePic;
//	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}