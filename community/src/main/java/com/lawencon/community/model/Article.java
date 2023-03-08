package com.lawencon.community.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_article",
uniqueConstraints = {
        @UniqueConstraint(name = "article_ck", 
                columnNames = {"photo", "user_id"}
        )})
public class Article extends BaseEntity {

	@Column(nullable = false, length = 50)
	private String articleTitle;
	
	@Column(nullable = false)
	private String articleContent;
	
	@OneToOne
	@JoinColumn(name = "photo")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
