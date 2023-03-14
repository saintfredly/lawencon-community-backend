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

	public Optional<UserProfile> getUserProfile(String userId) {
		UserProfile userProfile = null;
		try {
			final String sql = "SELECT tu.id,tu.full_name,tu.ver FROM t_user_profile tu "
					+ "WHERE tu.user_id=:userId AND tu.is_active=TRUE";
			final Object result = ConnHandler.getManager().createNativeQuery(sql).setParameter("userId", userId).getSingleResult();

			if (result != null) {
				userProfile = new UserProfile();
				final Object[] objArr = (Object[]) result;

				userProfile.setId(objArr[0].toString());
				userProfile.setFullName(objArr[1].toString());
				userProfile.setVersion(Integer.valueOf(objArr[2].toString()));
				
				
			}

		} catch (Exception e) {
			
		}

		return Optional.ofNullable(userProfile);
	}
}
