package com.careerfocus.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "testimonials")
public class Testimonial implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7771123685493038212L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "testimonial_id", columnDefinition = "INT")
	private int testimonialId;

	@Basic
	@Column(nullable = false)
	private String content;

	@Basic
	@Column(nullable = false, length = 1000)
	private String name;

	@Basic
	private String description;

	@Basic
	private String contact;

	@Basic
	@Column(name = "img_file_name")
	private String imgFileName;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "testimonial", cascade = CascadeType.MERGE)
	@JoinColumn(name = "testimonial_id", columnDefinition = "INT", nullable = false)
	private TestimonialImage testimonialImage;

	@Basic
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", columnDefinition = "INT", nullable = false)
	private User user;

	@PrePersist
	public void prePersist() {
		this.date = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.date = new Date();
	}

	public Testimonial() {
	}

	public Testimonial(int testimonialId) {
		this.testimonialId = testimonialId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public TestimonialImage getTestimonialImage() {
		return testimonialImage;
	}

	public void setTestimonialImage(TestimonialImage testimonialImage) {
		this.testimonialImage = testimonialImage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTestimonialId() {
		return testimonialId;
	}

	public void setTestimonialId(int testimonialId) {
		this.testimonialId = testimonialId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}