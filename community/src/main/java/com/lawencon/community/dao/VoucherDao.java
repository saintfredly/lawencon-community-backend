package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Voucher;

@Repository
public class VoucherDao extends BaseMasterDao<Voucher> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Voucher> getAll() {
		final String sql = "SELECT * FROM t_voucher WHERE t_voucher.is_active=TRUE";
		final List<Voucher> voucher = ConnHandler.getManager().createNativeQuery(sql, Voucher.class).getResultList();
		return voucher;
	}

	@Override
	public Optional<Voucher> getById(String id) {
		return Optional.ofNullable(super.getById(Voucher.class, id));
	}

	@Override
	public Optional<Voucher> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Voucher.class, id));
	}

}
