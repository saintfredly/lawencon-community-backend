package com.lawencon.community.pojo.userpremium;

import java.time.LocalDateTime;

public class PojoUserPremiumRes {
	private String id;
	private String proofOfPayment;
	private LocalDateTime datePayment;
	private String fullName;
	private Integer ver;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProofOfPayment() {
		return proofOfPayment;
	}

	public void setProofOfPayment(String proofOfPayment) {
		this.proofOfPayment = proofOfPayment;
	}

	public LocalDateTime getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

}
