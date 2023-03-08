package com.lawencon.community.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_polling",
uniqueConstraints = {
        @UniqueConstraint(name = "post_polling_ck", 
                columnNames = {"post_id", "photo"}
        )})
public class PostPolling extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@Column(nullable = false)
	private String pollingTitle;
	
	@OneToOne
	@JoinColumn(name = "photo")
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


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
