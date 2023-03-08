package com.lawencon.community.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.community.model.Category;

@Repository
public class CategoryDao extends BaseMasterDao<Category> {


	@Override
	List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	Optional<Category> getById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
