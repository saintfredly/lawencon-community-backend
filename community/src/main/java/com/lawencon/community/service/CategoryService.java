package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.model.Category;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.category.PojoCategoryReq;
import com.lawencon.community.util.GenerateId;

@Service
public class CategoryService {
	private final CategoryDao categoryDao;

	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public PojoRes save(PojoCategoryReq data) {

		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);

		Category category = new Category();

		if (data.getId() != null) {

			category = categoryDao.getByIdAndDetach(data.getId()).get();
			
			category.setCategoryName(data.getCategoryName());
			category.setVersion(data.getVer());

			category.setIsActive(data.getIsActive());

		} else {
			category.setCategoryCode(generateCode);
			category.setCategoryName(data.getCategoryName());
			category.setIsActive(true);
		}

		categoryDao.save(category);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;

	}
}
