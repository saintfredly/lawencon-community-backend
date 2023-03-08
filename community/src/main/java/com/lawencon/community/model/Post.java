package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post",
uniqueConstraints = {
        @UniqueConstraint(name = "post_ck", 
                columnNames = {"postType", "file", "category"}
        )})
public class Post extends BaseEntity {
	@Column(nullable = false, length = 50)
	private String postTitle;
	
	@Column(nullable = false, columnDefinition = "text")
	private String postContent;
	
	@OneToOne
	@JoinColumn(name = "post_type_id", nullable = false)
	private PostType postType;
	
	@OneToOne
	@JoinColumn(name = "photo")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

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

	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
