package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_type")
public class PostType extends BaseEntity {
	@Column(unique = true, nullable = false, length = 5)
	private String postTypeCode;
	
	@Column(nullable = false, length = 50)
	private String postTypeName;

	public String getPostTypeCode() {
		return postTypeCode;
	}

	public void setPostTypeCode(String postTypeCode) {
		this.postTypeCode = postTypeCode;
	}

	public String getPostTypeName() {
		return postTypeName;
	}

	public void setPostTypeName(String postTypeName) {
		this.postTypeName = postTypeName;
	}

}
