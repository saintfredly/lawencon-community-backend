package com.lawencon.community.pojo.post;

import java.time.LocalDateTime;
import java.util.List;

public class PojoPostRes {
	private String id;
	private String postTitle;
	private String postContent;
	private String postTypeId;
	private String photo;
	private String categoryId;
	private List<PojoPostCommentRes> theComment;
	private String createdBy;
	private LocalDateTime createdAt;
	private Boolean isLike;
	private Boolean isBookmark;
	private Integer ver;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<PojoPostCommentRes> getTheComment() {
		return theComment;
	}

	public void setTheComment(List<PojoPostCommentRes> theComment) {
		this.theComment = theComment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public Boolean getIsBookmark() {
		return isBookmark;
	}

	public void setIsBookmark(Boolean isBookmark) {
		this.isBookmark = isBookmark;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

}
