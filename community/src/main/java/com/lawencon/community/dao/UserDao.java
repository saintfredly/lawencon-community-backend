package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

@Repository
public class UserDao extends BaseMasterDao<User> {

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
	
	public Optional<User> getByIdRef(String id) {
		return Optional.ofNullable(super.getByIdRef(User.class, id));
	} 

	public Optional<User> getByEmail(String email) {
		User user = null;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT tu.id, tu.email, tu.passwords, tu.role_id, tr.role_code, tu.photo, ")
			.append("tu.created_by,tu.updated_by,tu.created_at,tu.updated_at, tu.ver, tu.is_active  ")
			.append("FROM t_user tu INNER JOIN t_role tr ON tr.id = tu.role_id ")
			.append("WHERE tu.email =:email AND tu.is_active=TRUE");
//			final String sql = "SELECT tu.id,tu.email,tu.passwords,tu.role_id,tr.role_code,tu.photo,"
//					+ "tu.created_by,tu.updated_by,tu.created_at,tu.updated_at"
//					+ ",tu.ver,tu.is_active FROM t_user tu INNER JOIN t_role tr ON tr.id = tu.role_id"
//					+ " WHERE tu.email =:email AND tu.is_active=TRUE";

			final Object result = ConnHandler.getManager().createNativeQuery(toStr(sql)).setParameter("email", email)
					.getSingleResult();

			if (result != null) {
				user = new User();
				final Object[] objArr = (Object[]) result;

				user.setId(objArr[0].toString());
				user.setEmail(objArr[1].toString());
				user.setPasswords(objArr[2].toString());

				final Role role = new Role();
				role.setId(objArr[3].toString());
				role.setRoleCode(objArr[4].toString());
				user.setRole(role);

				final String fileId = objArr[5].toString();
				final File file = new File();
				file.setId(fileId);

				user.setFile(file);

				user.setCreatedBy(objArr[6].toString());
				if (objArr[7] != null) {
					user.setUpdatedBy(objArr[7].toString());
				}

				user.setCreatedAt(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());

				if (objArr[9] != null) {
					user.setUpdatedAt(Timestamp.valueOf(objArr[9].toString()).toLocalDateTime());
				}
				user.setVersion(Integer.valueOf(objArr[10].toString()));
				user.setIsActive(Boolean.valueOf(objArr[11].toString()));
			}

		} catch (Exception e) {

		}

		return Optional.ofNullable(user);
	}
	
	
	public Optional<User> getUserByRoleCode(String code) {
		User user = null;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.id, u.role_id, r.role_code, u.email, ")
			.append("u.passwords, u.created_by, u.updated_by, u.created_at, u.updated_at, ")
			.append("u.is_active, u.ver ")
			.append("FROM t_user u ")
			.append("INNER JOIN t_role r ON r.id = u.role_id ")
			.append("WHERE r.role_code = :code ")
			.append("AND u.is_active = TRUE");
			
			final Object result = ConnHandler.getManager().createNativeQuery(toStr(sql))
				.setParameter("code", code)
				.getSingleResult();
			
			if(result != null) {
				user = new User();
				final Object[] objArr = (Object[]) result;
				
				user.setId(objArr[0].toString());
				
				final Role role = new Role();
				role.setId(objArr[1].toString());
				role.setRoleCode(objArr[2].toString());
				user.setRole(role);
				
				user.setEmail(objArr[3].toString());
				user.setPasswords(objArr[4].toString());
				
				user.setCreatedBy(objArr[5].toString());
				
				if(objArr[6] != null) {					
					user.setUpdatedBy(objArr[6].toString());
				}
				
				user.setCreatedAt(Timestamp.valueOf(objArr[7].toString()).toLocalDateTime());
				
				if(objArr[8] != null) {					
					user.setUpdatedAt(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
				}
				user.setIsActive(Boolean.valueOf(objArr[9].toString()));
				user.setVersion(Integer.valueOf(objArr[10].toString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(user);
	} 

}
