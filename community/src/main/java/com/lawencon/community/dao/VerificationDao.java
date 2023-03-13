package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Verification;

@Repository
public class VerificationDao extends BaseMasterDao<Verification>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Verification> getAll() {
		final String sql = "SELECT * FROM t_verification WHERE is_active = TRUE";
		final List<Verification> res = ConnHandler.getManager().createNativeQuery(sql, Verification.class).getResultList();		
		return res;
	}

	@Override
	public Optional<Verification> getById(String id) {
		return Optional.ofNullable(super.getById(Verification.class, id));
	}

	@Override
	public Optional<Verification> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Verification.class, id));
	}
	
	public Optional<Verification> getByVerificationCode(String email, String code) {
		Verification verification = null;
		
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT v.id, v.email, v.verification_code, v.expire, v.is_active, v.ver ")
			.append("FROM t_verification v ")
			.append("WHERE v.email = :email AND v.verification_code = :code ")
			.append("AND v.expire >= NOW() ")
			.append("AND v.is_active = TRUE");
			
			final Object result = ConnHandler.getManager().createNativeQuery(toStr(sql))
				.setParameter("email", email)
				.setParameter("code", code)
				.getSingleResult();
			
			if(result != null) {
				verification = new Verification();
				final Object[] objArr = (Object[]) result;
				
				verification.setId(objArr[0].toString());
				verification.setEmail(objArr[1].toString());
				verification.setVerificationCode(objArr[2].toString());
				if (objArr[3] != null) {
					verification.setExpire(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());
				}
				verification.setIsActive(Boolean.valueOf(objArr[4].toString()));
				verification.setVersion(Integer.valueOf(objArr[5].toString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(verification);
	}

}
