package com.lawencon.community.constant;

public enum ProductTypeEnum {
	COURSE("PR001", "Course"), EVENT("PR002", "Event");

	private String productTypeCode;
	private String productTypeName;

	ProductTypeEnum(String productTypeCode, String productTypeName) {
		this.productTypeCode = productTypeCode;
		this.productTypeName = productTypeName;
	}

	public String getProductTypeCode() {
		return productTypeCode;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

}
