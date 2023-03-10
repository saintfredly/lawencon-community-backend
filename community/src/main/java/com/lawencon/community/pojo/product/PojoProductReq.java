package com.lawencon.community.pojo.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lawencon.community.pojo.file.PojoFileReqInsert;

public class PojoProductReq {
	private String id;
	private String productName;
	private String productDescription;
	private String productLocation;
	private PojoFileReqInsert photo;
	private String providerName;
	private BigDecimal price;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private String categoryId;
	private String productTypeId;
	private Integer ver;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductLocation() {
		return productLocation;
	}

	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}

	public PojoFileReqInsert getPhoto() {
		return photo;
	}

	public void setPhoto(PojoFileReqInsert photo) {
		this.photo = photo;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
