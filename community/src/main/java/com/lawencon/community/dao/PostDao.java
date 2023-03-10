package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Post;

@Repository
public class PostDao extends BaseMasterDao<Post>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAll() {
		final String sql = "SELECT * FROM t_post WHERE t_post.is_active=TRUE";
		final List<Post> post = ConnHandler.getManager().createNativeQuery(sql, Post.class).getResultList();
		return post;
	}

	@Override
	public Optional<Post> getById(String id) {
		return Optional.ofNullable(super.getById(Post.class, id));
	}

	@Override
	public Optional<Post> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Post.class, id));
	}

}
