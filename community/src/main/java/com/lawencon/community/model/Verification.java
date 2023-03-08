package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_verification")
public class Verification extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 6, nullable = false)
	private String vCode;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}
}
