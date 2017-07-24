package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;
/**
 * Created by sarath on 10/06/17.
 */
@Entity
@Table(name="bundle")
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bundle_id", columnDefinition="INT")
    private int bundleId;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="bundle_category_id", columnDefinition="INT", nullable=false)
    private BundleCategory bundleCategory;

    @Basic
    private String description;

    @Basic
    @Column(name="image_url", length=2000)
    private String imageUrl;

    @Basic
    @Column(name="is_available")
    private boolean isAvailable;

    @Basic
    @Column(columnDefinition="FLOAT")
    private float mrp;

    @Basic
    private String name;

    @ManyToMany(targetEntity=QuestionPaper.class, cascade=CascadeType.MERGE)
    @JoinTable(name="bundle_question_paper", joinColumns=@JoinColumn(name="bundle_id", columnDefinition="INT", nullable=false), inverseJoinColumns=@JoinColumn(name="question_paper_id", columnDefinition="INT"))
    private Set<QuestionPaper> questionPapers = new HashSet<QuestionPaper>();

    @Basic
    @Column(name="selling_price", columnDefinition="FLOAT")
    private float sellingPrice;

    @Basic
    @Column(name="discount_percent", columnDefinition="FLOAT")
    private float discountPercent;
    
    @Basic
    @Column(name="coaching_type", columnDefinition="INT")
    private float coachingType;

    public Bundle() {
    }

    public Bundle(int bundleId) {
        this.bundleId = bundleId;
    }

    public BundleCategory getBundleCategory() {
        return bundleCategory;
    }

    public void setBundleCategory(BundleCategory bundleCategory) {
        this.bundleCategory = bundleCategory;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}
}