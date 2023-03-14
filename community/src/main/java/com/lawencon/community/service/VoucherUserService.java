package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.VoucherUserDao;

@Service
public class VoucherUserService {
	private final VoucherUserDao voucherUserDao;

	public VoucherUserService(VoucherUserDao voucherUserDao) {
		this.voucherUserDao = voucherUserDao;
	}
	
	
}
