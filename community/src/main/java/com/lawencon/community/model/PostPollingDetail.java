package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_polling_detail")
public class PostPollingDetail extends BaseEntity {
	private PostPolling postPolling;
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
