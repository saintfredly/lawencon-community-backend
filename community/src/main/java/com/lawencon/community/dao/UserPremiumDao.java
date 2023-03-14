package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.UserPremium;
@Repository
public class UserPremiumDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<UserPremium> getAll() {
		final String sql = "SELECT * FROM t_user_premium WHERE is_approved = FALSE AND is_active = TRUE";
		final List<UserPremium> res = ConnHandler.getManager().createNativeQuery(sql, UserPremium.class).getResultList();
		return res;
	}
}
