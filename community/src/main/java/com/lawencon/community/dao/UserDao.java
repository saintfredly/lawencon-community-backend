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
	List<User> getAll() {
		final String sql = "SELECT * FROM t_user WHERE is_active = TRUE";
		final List<User> res = ConnHandler.getManager().createNativeQuery(sql, User.class).getResultList();		
		return res;
	}

	@Override
	Optional<User> getById(Long id) {
		final User user = ConnHandler.getManager().find(User.class, id);
		return Optional.ofNullable(user);
	}

	

}
