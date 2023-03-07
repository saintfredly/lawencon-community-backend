package com.lawencon.community.model;

import java.time.LocalDateTime;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_voucher")
public class Voucher extends BaseEntity {

	@Column(unique = true, nullable = false, length = 5)
	private String voucherCode;
	
	@Column(unique = true, nullable = false, length = 50)
	private String voucherName;
	
	private String voucherDescription;
	
	@Column(unique = true, nullable = false)
	private Integer amount;
	
	@Column(unique = true, nullable = false)
	private LocalDateTime dateStart;
	
	@Column(unique = true, nullable = false)

	private LocalDateTime dateExpired;

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public String getVoucherDescription() {
		return voucherDescription;
	}

	public void setVoucherDescription(String voucherDescription) {
		this.voucherDescription = voucherDescription;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(LocalDateTime dateExpired) {
		this.dateExpired = dateExpired;
	}

}
