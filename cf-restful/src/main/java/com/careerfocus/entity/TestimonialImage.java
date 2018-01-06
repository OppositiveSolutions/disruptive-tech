package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "achiever_image")
public class TestimonialImage {

    @Id
    @Column(name = "achiever_id", columnDefinition = "INT")
    private int achieverId;

    @Basic
    @Column(columnDefinition = "BLOB", nullable = false)
    private byte[] image;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "achiever_id", columnDefinition = "INT")
    private Achievers achievers;

    public TestimonialImage() {
    }

    public TestimonialImage(int achieverId) {
        this.achieverId = achieverId;
    }

    public TestimonialImage(int achieverId, byte[] image) {
        this.achieverId = achieverId;
        this.image = image;
    }

    public int getachieverId() {
        return achieverId;
    }

    public void setachieverId(int achieverId) {
        this.achieverId = achieverId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Achievers getAchievers() {
        return achievers;
    }

    public void setAchievers(Achievers achievers) {
        this.achievers = achievers;
    }
}