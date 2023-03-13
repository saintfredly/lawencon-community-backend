package com.lawencon.community.pojo.post;

import java.time.LocalDateTime;

public class PojoPostCommentRes {
	private String fileId;
	private String fullName;
	private String theComment;
	private LocalDateTime createdAt;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTheComment() {
		return theComment;
	}

	public void setTheComment(String theComment) {
		this.theComment = theComment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
