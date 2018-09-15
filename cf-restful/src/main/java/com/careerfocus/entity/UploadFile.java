package com.careerfocus.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files_upload")
public class UploadFile {
	private int id;
	private String fileName;
	private byte[] data;
	private int coachingType = 1;
	private int isCurrent = 1;
	private int isFree = 1;
	private int level = 1;

	public UploadFile() {
		super();
	}

	public UploadFile(String fileName, byte[] data, int coachingType, int isCurrent) {
		super();
		this.coachingType = coachingType;
		this.fileName = fileName;
		this.data = data;
		this.isCurrent = isCurrent;
	}

	@Id
	@Column(name = "file_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Basic
	@Column(columnDefinition = "BLOB", name = "file_data", nullable = false)
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "coaching_type")
	public int getCoachingType() {
		return coachingType;
	}

	public void setCoachingType(int coachingType) {
		this.coachingType = coachingType;
	}

	@Column(name = "is_current")
	public int getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}

	@Column(name = "is_free")
	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	@Column(name = "level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
