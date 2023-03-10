package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.base.AbstractJpaDao;

public abstract class BaseMasterDao<T> extends AbstractJpaDao {

	abstract List<T> getAll();
	
	abstract Optional<T> getById(String id);
	
	abstract Optional<T> getByIdAndDetach(String id);
	
	protected String toStr(final StringBuilder str) {
		return str.toString();
	}
}
