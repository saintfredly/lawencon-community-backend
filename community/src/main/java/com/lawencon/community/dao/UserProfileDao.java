package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.UserProfile;

@Repository
public class UserProfileDao extends BaseMasterDao<UserProfile> {
	
	@SuppressWarnings("unchecked")
	@Override
	List<UserProfile> getAll() {
		final String sql = "SELECT * FROM t_user_profile WHERE is_active = TRUE";
		final List<UserProfile> res = ConnHandler.getManager().createNativeQuery(sql, UserProfile.class).getResultList();
		return res;
	}

	@Override
	Optional<UserProfile> getById(String id) {
		return Optional.ofNullable(super.getById(UserProfile.class, id));
	}

	@Override
	Optional<UserProfile> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(UserProfile.class, id));
	}

}
