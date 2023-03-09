package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.model.Category;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.category.PojoCategoryReq;
import com.lawencon.community.pojo.category.PojoCategoryRes;
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

		final Category category = new Category();

		category.setCategoryCode(generateCode);
		category.setCategoryName(data.getCategoryName());
		category.setIsActive(true);

		categoryDao.save(category);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;

	}

	public PojoRes update(PojoCategoryReq data) {

		ConnHandler.begin();

		Category category = new Category();

		category = categoryDao.getByIdAndDetach(data.getId()).get();

		category.setCategoryName(data.getCategoryName());
		category.setVersion(data.getVer());

		category.setIsActive(data.getIsActive());

		categoryDao.save(category);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Update Success!");
		return pojoRes;

	}

	public PojoRes deleteById(String id) {
		ConnHandler.begin();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Delete Success!");
		final PojoRes pojoResFail = new PojoRes();
		pojoResFail.setMessage("Delete Failed!");

		Boolean result = categoryDao.deleteById(Category.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}

	}

	public List<PojoCategoryRes> getAll() {
		final List<Category> category = categoryDao.getAll();
		final List<PojoCategoryRes> pojoResGet = new ArrayList<>();

		for (int i = 0; i < category.size(); i++) {
			final PojoCategoryRes pojoCategoryRes = new PojoCategoryRes();

			pojoCategoryRes.setCategoryCode(category.get(i).getCategoryCode());
			pojoCategoryRes.setCategoryName(category.get(i).getCategoryName());
			pojoCategoryRes.setCreatedAt(category.get(i).getCreatedAt());
			pojoCategoryRes.setIsActive(category.get(i).getIsActive());
			pojoCategoryRes.setVer(category.get(i).getVersion());

			pojoResGet.add(pojoCategoryRes);
		}

		return pojoResGet;

	}

	public Optional<Category> getById(String id) {
		return categoryDao.getById(id);

	}
}
