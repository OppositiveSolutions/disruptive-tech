package com.careerfocus.entity;

import java.util.*;
import javax.persistence.*;
/**
 * Created by sarath on 10/06/17.
 */
//@Entity
//@Table(name="bundle")
//public class Bundle {
//    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
//    @JoinColumn(name="bundle_id", columnDefinition="INT")
//    private BundleCategory bundleCategory;
//
//    @Basic
//    @Column(name="bundle_category_id", columnDefinition="INT")
//    private int bundleCategoryId;
//
//    @Id
//    @Column(name="bundle_id", columnDefinition="INT")
//    private int bundleId;
//
//    @OneToMany(targetEntity=BundleQuestionPaper.class, mappedBy="bundle", cascade=CascadeType.MERGE)
//    private Set<BundleQuestionPaper> bundleQuestionPapers = new HashSet<BundleQuestionPaper>();
//
//    @Basic
//    private String description;
//
//    @Basic
//    @Column(name="image_url", length=2000)
//    private String imageUrl;
//
//    @Basic
//    @Column(name="is_available")
//    private boolean isAvailable;
//
//    @Basic
//    @Column(columnDefinition="FLOAT")
//    private float mrp;
//
//    @Basic
//    private String name;
//
//    @Basic
//    @Column(name="selling_price", columnDefinition="FLOAT")
//    private float sellingPrice;
//
//
//    public Bundle() {
//    }
//
//    public Bundle(int bundleId) {
//        this.bundleId = bundleId;
//    }
//
//    public BundleCategory getBundleCategory() {
//        return bundleCategory;
//    }
//
//    public void setBundleCategory(BundleCategory bundleCategory) {
//        this.bundleCategory = bundleCategory;
//    }
//
//    public int getBundleCategoryId() {
//        return bundleCategoryId;
//    }
//
//    public void setBundleCategoryId(int bundleCategoryId) {
//        this.bundleCategoryId = bundleCategoryId;
//    }
//
//    public int getBundleId() {
//        return bundleId;
//    }
//
//    public void setBundleId(int bundleId) {
//        this.bundleId = bundleId;
//    }
//
//    public Set<BundleQuestionPaper> getBundleQuestionPapers() {
//        return bundleQuestionPapers;
//    }
//
//    public void setBundleQuestionPapers(Set<BundleQuestionPaper> bundleQuestionPapers) {
//        this.bundleQuestionPapers = bundleQuestionPapers;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public boolean isIsAvailable() {
//        return isAvailable;
//    }
//
//    public void setIsAvailable(boolean isAvailable) {
//        this.isAvailable = isAvailable;
//    }
//
//    public float getMrp() {
//        return mrp;
//    }
//
//    public void setMrp(float mrp) {
//        this.mrp = mrp;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public float getSellingPrice() {
//        return sellingPrice;
//    }
//
//    public void setSellingPrice(float sellingPrice) {
//        this.sellingPrice = sellingPrice;
//    }
//}