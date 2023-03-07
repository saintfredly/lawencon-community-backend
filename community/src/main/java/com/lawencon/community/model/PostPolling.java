package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_polling")
public class PostPolling extends BaseEntity {
	private Post post;
	private String pollingTitle;
	private File file;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getPollingTitle() {
		return pollingTitle;
	}

	public void setPollingTitle(String pollingTitle) {
		this.pollingTitle = pollingTitle;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

}
