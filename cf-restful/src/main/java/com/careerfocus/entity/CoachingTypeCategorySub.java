package com.careerfocus.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "coaching_type_category_sub")
public class CoachingTypeCategorySub {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coaching_type_category_sub_id", columnDefinition = "INT")
	private int coachingTypeCategorySubId;

	@Basic
	@Column(nullable = false, length = 1000)
	private String name;
	
	@Basic
	@Column(name = "coaching_type_category_id")
	private int coachingTypeCategoryId;

	@Basic
	@Column(name = "is_delete")
	private int isDelete;

	@Basic
	@Column(name = "img_file_name")
	private String imgFileName;
	
	@OneToMany(targetEntity = CoachingTypeCategorySubImage.class, mappedBy = "coachingTypeCategorySub", cascade = CascadeType.ALL)
	private Set<CoachingTypeCategorySubImage> coachingTypeCategorySubImage = new HashSet<CoachingTypeCategorySubImage>();
	
	@OneToMany
    @JoinColumn(name = "coaching_type_category_sub_id")
    @OrderBy("coaching_type_category_sub_id")
    private Set<CoachingTypeCategorySubUnit> coachingTypeCategorySubUnits = new HashSet<CoachingTypeCategorySubUnit>();

	public CoachingTypeCategorySub() {
	}

	public CoachingTypeCategorySub(int coachingTypeCategorySubId) {
		this.coachingTypeCategorySubId = coachingTypeCategorySubId;
	}

	public int getCoachingTypeCategorySubId() {
		return coachingTypeCategorySubId;
	}

	public void setCoachingTypeCategorySubId(int coachingTypeCategorySubId) {
		this.coachingTypeCategorySubId = coachingTypeCategorySubId;
	}

	public int getCoachingTypeCategoryId() {
		return coachingTypeCategoryId;
	}

	public void setCoachingTypeCategoryId(int coachingTypeCategoryId) {
		this.coachingTypeCategoryId = coachingTypeCategoryId;
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

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public Set<CoachingTypeCategorySubImage> getCoachingTypeCategorySubImage() {
		return coachingTypeCategorySubImage;
	}

	public void setCoachingTypeCategorySubImage(Set<CoachingTypeCategorySubImage> coachingTypeCategorySubImage) {
		this.coachingTypeCategorySubImage = coachingTypeCategorySubImage;
	}
	
	public Set<CoachingTypeCategorySubUnit> getCoachingTypeCategorySubUnits() {
        return coachingTypeCategorySubUnits;
	}

	public void setCoachingTypeCategorySubUnits(Set<CoachingTypeCategorySubUnit> coachingTypeCategorySubUnits) {
        this.coachingTypeCategorySubUnits = coachingTypeCategorySubUnits;
	}

	@Override
	public String toString() {
		return String.format("coachingTypeCategorySubId: %d | name: %s | isDelete: %d", coachingTypeCategorySubId, name,
				isDelete);
	}
}