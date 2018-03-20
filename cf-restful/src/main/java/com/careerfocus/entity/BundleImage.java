package com.careerfocus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Auto-generated by:
 * org.apache.openjpa.jdbc.meta.ReverseMappingTool$AnnotatedCodeGenerator
 */
@Entity
@Table(name = "bundle_image")
public class BundleImage {

	@Id
	@Column(name = "bundle_id", columnDefinition = "INT")
	private int bundleId;

	@Basic
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] image;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "bundle_id", columnDefinition = "INT")
	private Bundle bundle;

	public BundleImage() {
	}

	public BundleImage(int bundleId) {
		this.bundleId = bundleId;
	}

	public BundleImage(int bundleId, byte[] image) {
		this.bundleId = bundleId;
		this.image = image;
	}

	public int getBundleId() {
		return bundleId;
	}

	public void setBundleId(int bundleId) {
		this.bundleId = bundleId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
}