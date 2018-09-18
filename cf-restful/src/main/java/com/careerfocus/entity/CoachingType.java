package com.careerfocus.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "coaching_type")
public class CoachingType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coaching_type_id", columnDefinition = "INT")
	private int coachingTypeId;

	@Basic
	@Column(nullable = false, length = 1000)
	private String name;

	@Basic
	@Column(name = "is_delete")
	private int isDelete;

	@OneToMany
    @JoinColumn(name = "coaching_type_id")
    @OrderBy("coaching_type_id")
    private Set<CoachingTypeCategory> coachingTypeCategorys = new HashSet<CoachingTypeCategory>();

	public CoachingType() {
	}

	public CoachingType(int coachingTypeId) {
		this.coachingTypeId = coachingTypeId;
	}

	public int getCoachingTypeId() {
		return coachingTypeId;
	}

	public void setCoachingTypeId(int coachingTypeId) {
		this.coachingTypeId = coachingTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int isDelete() {
		return isDelete;
	}

	public void setDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Set<CoachingTypeCategory> getCoachingTypeCategorys() {
	        return coachingTypeCategorys;
	}

	public void setCoachingTypeCategorys(Set<CoachingTypeCategory> coachingTypeCategorys) {
	        this.coachingTypeCategorys = coachingTypeCategorys;
	}

	@Override
	public String toString() {
		return String.format("coachingTypeCategoryId: %d | name: %s | isDelete: %d", coachingTypeId, name,
				isDelete);
	}
}