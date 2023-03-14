package com.lawencon.community.pojo.voucher;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PojoVoucherReq {
	private String id;
	private String voucherCode;
	private String voucherName;
	private String voucherDescription;
	private Integer amount;
	private LocalDateTime dateStart;
	private LocalDateTime dateExpired;
	private BigDecimal maxDiscount;
	private BigDecimal minTransaction;
	private Integer ver;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(BigDecimal maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public BigDecimal getMinTransaction() {
		return minTransaction;
	}

	public void setMinTransaction(BigDecimal minTransaction) {
		this.minTransaction = minTransaction;
	}

}
