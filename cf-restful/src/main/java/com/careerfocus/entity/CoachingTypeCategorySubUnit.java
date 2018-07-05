package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "coaching_type_category_sub_unit")
public class CoachingTypeCategorySubUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coaching_type_category_sub_unit_id", columnDefinition = "INT")
	private int coachingTypeCategorySubUnitId;

	@Basic
	@Column(nullable = false, length = 1000)
	private String name;

	@Basic
	@Column(name = "coaching_type_category_sub_id")
	private int coachingTypeCategorySubId;

	@Basic
	@Column(name = "is_delete")
	private int isDelete;

	@Basic
	@Column(name = "img_file_name")
	private String imgFileName;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "coachingTypeCategorySubUnit", cascade = CascadeType.MERGE)
	private CoachingTypeCategorySubUnitImage coachingTypeCategorySubUnitImage;

	public CoachingTypeCategorySubUnit() {
	}

	public CoachingTypeCategorySubUnit(int coachingTypeCategorySubUnitId) {
		this.coachingTypeCategorySubUnitId = coachingTypeCategorySubUnitId;
	}

	public int getCoachingTypeCategorySubUnitId() {
		return coachingTypeCategorySubUnitId;
	}

	public void setCoachingTypeCategoryId(int coachingTypeCategorySubUnitId) {
		this.coachingTypeCategorySubUnitId = coachingTypeCategorySubUnitId;
	}

	public int getCoachingTypeCategorySubId() {
		return coachingTypeCategorySubId;
	}

	public void setCoachingTypeCategorySubId(int coachingTypeCategorySubId) {
		this.coachingTypeCategorySubId = coachingTypeCategorySubId;
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

	public CoachingTypeCategorySubUnitImage getCoachingTypeCategorySubUnitImage() {
		return coachingTypeCategorySubUnitImage;
	}

	public void setCoachingTypeCategorySubUnitImage(CoachingTypeCategorySubUnitImage coachingTypeCategorySubUnitImage) {
		this.coachingTypeCategorySubUnitImage = coachingTypeCategorySubUnitImage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("coachingTypeCategorySubUnitId: %d | name: %s | isDelete: %d",
				coachingTypeCategorySubUnitId, name, isDelete);
	}
}