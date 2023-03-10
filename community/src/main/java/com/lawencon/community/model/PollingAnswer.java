package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling_answer",
uniqueConstraints = {
        @UniqueConstraint(name = "polling_answer_ck", 
                columnNames = {"post_polling_detail_id", "user_id"}
        )})
public class PollingAnswer extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "post_polling_detail_id")
	private PostPollingDetail postPollingDetail;
	
	@OneToOne
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
