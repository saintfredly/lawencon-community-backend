package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user",
uniqueConstraints = {
        @UniqueConstraint(name = "user_bk", 
                columnNames = {"email", "verificationCode"}
        ), @UniqueConstraint(name = "user_ck", 
                columnNames = {"role_id", "photo"}
        )})
public class User extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String passwords;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "photo")
	private File file;
	
	@Column(length = 6, nullable = false)
	private String verificationCode;
	
	private Boolean isVerified;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

}
