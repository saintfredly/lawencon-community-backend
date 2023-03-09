package com.lawencon.community.constant;

public enum PostTypeEnum {
	REGULAR("PT001", "Biasa"), POLLING("PT002", "Polling"), PREMIUM("PT003", "Premium");

	private String postTypeCode;
	private String postTypeName;

	PostTypeEnum(String postTypeCode, String postTypeName) {
		this.postTypeCode = postTypeCode;
		this.postTypeName = postTypeName;
	}

	public String getPostTypeCode() {
		return postTypeCode;
	}

	public String getPostTypeName() {
		return postTypeName;
	}
	
}
