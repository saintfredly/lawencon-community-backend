package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Category;

@Repository
public class CategoryDao extends BaseMasterDao<Category> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		final String sql = "SELECT * FROM t_category WHERE t_category.is_active=TRUE";
		final List<Category> category = ConnHandler.getManager().createNativeQuery(sql, Category.class).getResultList();
		return category;
	}

	@Override
	public Optional<Category> getById(String id) {
		return Optional.ofNullable(super.getById(Category.class, id));
	}

	@Override
	public Optional<Category> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Category.class, id));
	}

}
