package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling_answer")
public class PollingAnswer extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "post_polling_detail_id")
	private PostPollingDetail postPollingDetail;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public PostPollingDetail getPostPollingDetail() {
		return postPollingDetail;
	}

	public void setPostPollingDetail(PostPollingDetail postPollingDetail) {
		this.postPollingDetail = postPollingDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
