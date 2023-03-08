package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_comment",
uniqueConstraints = {
        @UniqueConstraint(name = "post_comment_ck", 
                columnNames = {"post_id", "user_id"}
        )})
public class PostComment extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@Column(name = "the_comment", nullable = false)
	private String theComment;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getTheComment() {
		return theComment;
	}

	public void setTheComment(String theComment) {
		this.theComment = theComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
