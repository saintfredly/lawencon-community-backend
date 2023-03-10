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
@Table(name = "t_payment",
uniqueConstraints = {
        @UniqueConstraint(name = "payment_ck", 
                columnNames = {"proof_of_payment", "user_id", "voucher_id", "product_id"}
        )})
public class Payment extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(nullable = false)
	private LocalDateTime datePayment;
	
	@Column(nullable = false)
	private BigDecimal grandTotal;
	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
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
