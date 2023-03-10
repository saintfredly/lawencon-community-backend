package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Role;

@Repository
public class RoleDao extends BaseMasterDao<Role> {

	@SuppressWarnings("unchecked")
	@Override
	List<Role> getAll() {
		final String sql = "SELECT * FROM t_role WHERE is_active = TRUE";
		final List<Role> res = ConnHandler.getManager().createNativeQuery(sql, Role.class).getResultList();
		return res;
	}

	@Override
	Optional<Role> getById(String id) {
		return Optional.ofNullable(super.getById(Role.class, id));
	}

	@Override
	Optional<Role> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Role.class, id));
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Role> getRoleByRoleCode(String roleCode) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_role ")
		.append("WHERE role_code = :roleCode AND is_active = TRUE");
		
		final List<Role> roles = ConnHandler.getManager().createNativeQuery(toStr(sql), Role.class)
				.setParameter("roleCode", roleCode).getResultList();
		
		return Optional.ofNullable(roles.get(0));
	}
	
}
