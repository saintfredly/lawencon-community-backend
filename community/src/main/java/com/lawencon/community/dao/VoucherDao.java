package com.lawencon.community.dao;

import java.sql.Timestamp;
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
	
	public Optional<Voucher> getByIdRef(String id) {
		return Optional.ofNullable(super.getByIdRef(Voucher.class, id));
	} 
	
	public Optional<Voucher> getByVoucherCode(String code) {
		Voucher voucher = null;
		
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, voucher_code, voucher_name, voucher_description, amount, date_expired, ")
			.append("created_by, updated_by, created_at, updated_at, is_active, ver ")
			.append("FROM t_voucher ")
			.append("WHERE voucher_code = :code ")
			.append("AND date_expired >= NOW() ")
			.append("AND is_active = TRUE");
			
			final Object result = ConnHandler.getManager().createNativeQuery(toStr(sql))
				.setParameter("code", code)
				.getSingleResult();
			
			if(result != null) {
				voucher = new Voucher();
				final Object[] objArr = (Object[]) result;
				
				voucher.setId(objArr[0].toString());
				voucher.setVoucherCode(objArr[1].toString());
				voucher.setVoucherName(objArr[2].toString());
				voucher.setVoucherDescription(objArr[3].toString());
				voucher.setAmount(Integer.valueOf(objArr[4].toString()));
				voucher.setDateExpired(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
				
				voucher.setCreatedBy(objArr[6].toString());
				
				if(objArr[7] != null) {					
					voucher.setUpdatedBy(objArr[7].toString());
				}
				
				voucher.setCreatedAt(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
				
				if(objArr[9] != null) {					
					voucher.setUpdatedAt(Timestamp.valueOf(objArr[9].toString()).toLocalDateTime());
				}
				voucher.setIsActive(Boolean.valueOf(objArr[10].toString()));
				voucher.setVersion(Integer.valueOf(objArr[11].toString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(voucher);
	}

}
