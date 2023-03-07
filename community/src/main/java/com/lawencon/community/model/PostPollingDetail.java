package com.lawencon.community.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_polling_detail")
public class PostPollingDetail extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "post_polling_id")
	private PostPolling postPolling;
	
	@Column(nullable = false)
	private String optionContent;

	public PostPolling getPostPolling() {
		return postPolling;
	}

	public void setPostPolling(PostPolling postPolling) {
		this.postPolling = postPolling;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

}
