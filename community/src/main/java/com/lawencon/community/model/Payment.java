package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_payment",
uniqueConstraints = {
        @UniqueConstraint(name = "payment_ck", 
                columnNames = {"file", "user", "voucher"}
        )})
public class Payment extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher;
	
	@Column(nullable = false)
	private LocalDateTime datePayment;
	
	@Column(nullable = false)
	private Float grandTotal;
	
	@OneToOne
	@JoinColumn(name = "proof_of_payment")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	private Boolean isApproved;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public LocalDateTime getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}

	public Float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Float grandTotal) {
		this.grandTotal = grandTotal;
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
