package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_like",
uniqueConstraints = {
        @UniqueConstraint(name = "post_like_ck", 
                columnNames = {"post", "user"}
        )})
public class PostLike extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	private Boolean status;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
