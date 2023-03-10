package com.lawencon.community.pojo.post;

import com.lawencon.community.pojo.file.PojoFileReq;

public class PojoPostReq {
	private String id;
	private String postTitle;
	private String postContent;
	private String postTypeId;
	private PojoFileReq photo;
	private String categoryId;
	private PojoPostPollingReq polling;
	private Integer ver;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostTypeId() {
		return postTypeId;
	}

	public void setPostTypeId(String postTypeId) {
		this.postTypeId = postTypeId;
	}

	public PojoFileReq getPhoto() {
		return photo;
	}

	public void setPhoto(PojoFileReq photo) {
		this.photo = photo;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public PojoPostPollingReq getPolling() {
		return polling;
	}

	public void setPolling(PojoPostPollingReq polling) {
		this.polling = polling;
	}

}
