package com.lawencon.community.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.User;

@Repository
public class UserDao extends BaseMasterDao<User>{

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final String sql = "SELECT * FROM t_user WHERE is_active = TRUE";
		final List<User> res = ConnHandler.getManager().createNativeQuery(sql, User.class).getResultList();		
		return res;
	}

	@Override
	public Optional<User> getById(String id) {
		return Optional.ofNullable(super.getById(User.class, id));
	}

	@Override
	public Optional<User> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(User.class, id));
	}	

}
