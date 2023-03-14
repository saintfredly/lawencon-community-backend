package com.lawencon.community.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_premium",
uniqueConstraints = {
        @UniqueConstraint(name = "user_premium_ck", 
                columnNames = {"proof_of_payment", "user_id"}
        )})
public class UserPremium extends BaseEntity {

	@Column(nullable = false)
	private LocalDateTime datePayment;

	@Column(nullable = false)
	private BigDecimal price;

	@OneToOne
	@JoinColumn(name = "proof_of_payment")
	private File file;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	private Boolean isApproved;

	public LocalDateTime getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

}
