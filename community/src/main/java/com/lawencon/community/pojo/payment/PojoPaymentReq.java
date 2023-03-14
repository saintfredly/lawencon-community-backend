package com.lawencon.community.pojo.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lawencon.community.pojo.file.PojoFileReq;

public class PojoPaymentReq {
	private String id;
	private String voucherCode;
	private String productId;
	private LocalDateTime datePayment;
	private BigDecimal grandTotal;
	private PojoFileReq photo;
	private String userId;
	private Boolean isApproved;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public LocalDateTime getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public PojoFileReq getPhoto() {
		return photo;
	}

	public void setPhoto(PojoFileReq photo) {
		this.photo = photo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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
}
