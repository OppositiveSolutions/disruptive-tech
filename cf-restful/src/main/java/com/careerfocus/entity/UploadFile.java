package com.careerfocus.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "FILES_UPLOAD")
public class UploadFile {
	private long id;
	private String fileName;
	private byte[] data;
	private int coachingType;

	public UploadFile() {
		super();
	}

	public UploadFile(String fileName, byte[] data, int coachingType) {
		super();
		this.coachingType = coachingType;
		this.fileName = fileName;
		this.data = data;
	}

	@Id
	@GeneratedValue
	@Column(name = "file_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
}
