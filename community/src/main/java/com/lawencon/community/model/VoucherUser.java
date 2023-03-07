package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_voucher_user")
public class VoucherUser extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher;
	
	@OneToOne
	@JoinColumn(name = "user_id")

	private User user;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
