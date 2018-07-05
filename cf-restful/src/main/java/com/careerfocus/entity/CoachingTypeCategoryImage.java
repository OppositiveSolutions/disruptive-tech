package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "coaching_type_category_image")
public class CoachingTypeCategoryImage {

    @Id
    @Column(name = "coaching_type_category_id", columnDefinition = "INT")
    private int coachingTypeCategoryId;

    @Basic
    @Column(columnDefinition = "BLOB", nullable = false)
    private byte[] image;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "coaching_type_category_id", columnDefinition = "INT")
    private CoachingTypeCategory coachingTypeCategory;

    public CoachingTypeCategoryImage() {
    }

    public CoachingTypeCategoryImage(int coachingTypeCategoryId) {
        this.coachingTypeCategoryId = coachingTypeCategoryId;
    }

    public CoachingTypeCategoryImage(int coachingTypeCategoryId, byte[] image) {
        this.coachingTypeCategoryId = coachingTypeCategoryId;
        this.image = image;
    }

}