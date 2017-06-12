package com.careerfocus.entity;

import javax.persistence.*;

@Entity
@Table(name = "video_tutorials")
public class VideoTutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_tutorial_id")
    int videoTutorialId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "url")
    String url;

    public int getVideoTutorialId() {
        return videoTutorialId;
    }

    public void setVideoTutorialId(int videoTutorialId) {
        this.videoTutorialId = videoTutorialId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
