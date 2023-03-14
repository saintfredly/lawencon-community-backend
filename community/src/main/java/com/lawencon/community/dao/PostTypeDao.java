package com.lawencon.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.PostType;

@Repository
public class PostTypeDao extends AbstractJpaDao{
	
	@SuppressWarnings("unchecked")
	public List<PostType> getAll() {
		final String sql = "SELECT * FROM t_post_type WHERE t_post_type.is_active=TRUE";
		final List<PostType> postType = ConnHandler.getManager().createNativeQuery(sql, PostType.class).getResultList();
		return postType;
	}
}
