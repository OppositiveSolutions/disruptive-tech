package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "states")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class States {

    @Id
    @Column(name = "state_id", columnDefinition = "INT")
    private int stateId;

    @Basic
    @Column(nullable = false, length = 100)
    private String name;


    public States() {
    }

    public States(int stateId) {
        this.stateId = stateId;
    }

    public States(int stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}