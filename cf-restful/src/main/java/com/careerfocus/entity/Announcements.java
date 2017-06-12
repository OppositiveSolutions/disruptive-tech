package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "announcements")
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "announcement_id", columnDefinition = "INT")
    private int announcementId;

    @Basic
    @Column(nullable = false, length = 1000)
    private String name;

    @Basic
    private String description;

    @Basic
    @Column(name = "is_current")
    private boolean isCurrent;

    @Basic
    @Column(name = "announcement_order", columnDefinition = "INT")
    private int order;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "announcements", cascade = CascadeType.MERGE)
    private AnnouncementImage announcementImage;

    public Announcements() {
    }

    public Announcements(int announcementId) {
        this.announcementId = announcementId;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public AnnouncementImage getAnnouncementImage() {
        return announcementImage;
    }

    public void setAnnouncementImage(AnnouncementImage announcementImage) {
        this.announcementImage = announcementImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("announcementId: %d | name: %s | description: %s | isCurrent: %b | order: %d",
                announcementId, name, description, isCurrent, order);
    }
}