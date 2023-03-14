package com.lawencon.community.pojo.userpremium;

import com.lawencon.community.pojo.file.PojoFileReq;

public class PojoUserPremiumReq {
	private String id;
	private PojoFileReq proofOfPayment;
	private Integer ver;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PojoFileReq getProofOfPayment() {
		return proofOfPayment;
	}

	public void setProofOfPayment(PojoFileReq proofOfPayment) {
		this.proofOfPayment = proofOfPayment;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

}
