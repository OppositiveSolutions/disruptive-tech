package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by sarath on 10/06/17.
 */
@Entity
@Table(name = "bundle")
public class Bundle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bundle_id", columnDefinition = "INT")
	private int bundleId;

	@Basic
	private String description;

	@Basic
	@Column(name = "is_available")
	private int isAvailable;

	@Basic
	@Column(columnDefinition = "FLOAT")
	private float mrp;

	@Basic
	private String name;

	@Basic
	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate = new Date();

	@Basic
	@Column(name = "validity_days", columnDefinition = "INT")
	private Integer validityDays = 60;

	@ManyToMany(targetEntity = QuestionPaper.class, cascade = CascadeType.MERGE)
	@JoinTable(name = "bundle_question_paper", joinColumns = @JoinColumn(name = "bundle_id", columnDefinition = "INT", nullable = false), inverseJoinColumns = @JoinColumn(name = "question_paper_id", columnDefinition = "INT"))
	private Set<QuestionPaper> questionPapers = new HashSet<QuestionPaper>();

	@Basic
	@Column(name = "selling_price", columnDefinition = "FLOAT")
	private float sellingPrice;

	@Basic
	@Column(name = "discount_percent", columnDefinition = "FLOAT")
	private float discountPercent;

	@Basic
	@Column(name = "coaching_type", columnDefinition = "INT")
	private float coachingType;

	@Basic
	@Column(name = "img_file_name")
	private String imgFileName;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bundle", cascade = CascadeType.MERGE)
	private BundleImage bundleImage;

	public Bundle() {
	}

	public Bundle(int bundleId) {
		this.bundleId = bundleId;
	}

	public int getBundleId() {
		return bundleId;
	}

	public void setBundleId(int bundleId) {
		this.bundleId = bundleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}

	public float getCoachingType() {
		return coachingType;
	}

	public void setCoachingType(float coachingType) {
		this.coachingType = coachingType;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<QuestionPaper> getQuestionPapers() {
		return questionPapers;
	}

	public void setQuestionPapers(Set<QuestionPaper> questionPapers) {
		this.questionPapers = questionPapers;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public BundleImage getBundleImage() {
		return bundleImage;
	}

	public void setBundleImage(BundleImage bundleImage) {
		this.bundleImage = bundleImage;
	}
}