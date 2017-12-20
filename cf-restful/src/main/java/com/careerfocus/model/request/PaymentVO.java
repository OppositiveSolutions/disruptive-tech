package com.careerfocus.model.request;

import java.io.Serializable;

public class PaymentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2416783931439715568L;
	private String key;
	private String txnid;
	private float amount;
	private String productinfo;
	private String firstname;
	private String email;
	private String phone;
	private String surl;
	private String furl;
	private String hash;
	private String service_provider = "payu_paisa";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getService_provider() {
		return service_provider;
	}

	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}

}
