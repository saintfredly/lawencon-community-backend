package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_bookmark")
public class PostBookmark extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "status")
	private boolean status;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
