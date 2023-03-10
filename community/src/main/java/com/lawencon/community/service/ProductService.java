package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.constant.ProductTypeEnum;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProductDao;
import com.lawencon.community.dao.ProductTypeDao;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Product;
import com.lawencon.community.model.ProductType;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.product.PojoProductRes;
import com.lawencon.community.pojo.product.PojoProductReq;
import com.lawencon.community.util.GenerateId;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final FileDao fileDao;
	private final ProductTypeDao productTypeDao;
	private final CategoryDao categoryDao;

	public ProductService(ProductDao productDao, FileDao fileDao, ProductTypeDao productTypeDao,
			CategoryDao categoryDao) {
		this.productDao = productDao;
		this.fileDao = fileDao;
		this.productTypeDao = productTypeDao;
		this.categoryDao = categoryDao;
	}

	public PojoRes save(PojoProductReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);

		Product product = new Product();
		product.setProductCode(generateCode);
		product.setProductName(data.getProductName());
		product.setProductDescription(data.getProductDescription());
		product.setProductLocation(data.getProductLocation());

		final File file = new File();
		file.setFileName(data.getPhoto().getFileName());
		file.setFileContent(data.getPhoto().getFileContent());
		file.setFileExtension(data.getPhoto().getFileExtension());
		file.setIsActive(true);

		fileDao.save(file);

		product.setFile(file);

		product.setProviderName(data.getProviderName());
		product.setPrice(data.getPrice());
		product.setDateStart(data.getDateStart());
		product.setDateEnd(data.getDateEnd());

		final Category category = categoryDao.getByIdRef(Category.class, data.getCategoryId());

		product.setCategory(category);

		final ProductType productType = productTypeDao.getByIdRef(ProductType.class, data.getProductTypeId());

		product.setProductType(productType);
		product.setIsActive(true);

		productDao.save(product);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}

	public PojoRes update(PojoProductReq data) {
		ConnHandler.begin();

		Product product = new Product();
		product = productDao.getByIdAndDetach(data.getId()).get();

		product.setProductName(data.getProductName());
		product.setProductDescription(data.getProductDescription());
		product.setProductLocation(data.getProductLocation());

		final File file = new File();
		file.setFileName(data.getPhoto().getFileName());
		file.setFileContent(data.getPhoto().getFileContent());
		file.setFileExtension(data.getPhoto().getFileExtension());
		file.setIsActive(true);

		fileDao.save(file);

		product.setFile(file);

		product.setProviderName(data.getProviderName());
		product.setPrice(data.getPrice());
		product.setDateStart(data.getDateStart());
		product.setDateEnd(data.getDateEnd());

		final Category category = categoryDao.getByIdRef(Category.class, data.getCategoryId());

		product.setCategory(category);

		final ProductType productType = productTypeDao.getByIdRef(ProductType.class, data.getProductTypeId());

		product.setProductType(productType);

		product.setVersion(data.getVer());
		product.setIsActive(data.getIsActive());

		productDao.save(product);
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

		Boolean result = productDao.deleteById(Product.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}

	public List<PojoProductRes> getAllCourse() {
		final List<Product> product = productDao.getAllByProductType(ProductTypeEnum.COURSE.getProductTypeCode());
		final List<PojoProductRes> pojoResGet = new ArrayList<>();

		for (int i = 0; i < product.size(); i++) {
			final PojoProductRes pojoCourseResGetAll = new PojoProductRes();
			
			pojoCourseResGetAll.setId(product.get(i).getId());
			pojoCourseResGetAll.setProductCode(product.get(i).getProductCode());
			pojoCourseResGetAll.setProductName(product.get(i).getProductName());
			pojoCourseResGetAll.setProductDescription(product.get(i).getProductDescription());
			pojoCourseResGetAll.setProductLocation(product.get(i).getProductLocation());
			pojoCourseResGetAll.setProviderName(product.get(i).getProviderName());
			pojoCourseResGetAll.setPhoto(product.get(i).getFile().getId());
			pojoCourseResGetAll.setPrice(product.get(i).getPrice());
			pojoCourseResGetAll.setDateStart(product.get(i).getDateStart());
			pojoCourseResGetAll.setDateEnd(product.get(i).getDateEnd());
			pojoCourseResGetAll.setProductTypeId(product.get(i).getProductType().getId());
			pojoCourseResGetAll.setCategoryId(product.get(i).getCategory().getId());
			pojoCourseResGetAll.setVer(product.get(i).getVersion());
			pojoCourseResGetAll.setIsActive(product.get(i).getIsActive());
			
			pojoResGet.add(pojoCourseResGetAll);
		}

		return pojoResGet;
	}
	
	public List<PojoProductRes> getAllEvent() {
		final List<Product> product = productDao.getAllByProductType(ProductTypeEnum.EVENT.getProductTypeCode());
		final List<PojoProductRes> pojoResGet = new ArrayList<>();

		for (int i = 0; i < product.size(); i++) {
			final PojoProductRes pojoEventResGetAll = new PojoProductRes();
			
			pojoEventResGetAll.setId(product.get(i).getId());
			pojoEventResGetAll.setProductCode(product.get(i).getProductCode());
			pojoEventResGetAll.setProductName(product.get(i).getProductName());
			pojoEventResGetAll.setProductDescription(product.get(i).getProductDescription());
			pojoEventResGetAll.setProductLocation(product.get(i).getProductLocation());
			pojoEventResGetAll.setProviderName(product.get(i).getProviderName());
			pojoEventResGetAll.setPhoto(product.get(i).getFile().getId());
			pojoEventResGetAll.setPrice(product.get(i).getPrice());
			pojoEventResGetAll.setDateStart(product.get(i).getDateStart());
			pojoEventResGetAll.setDateEnd(product.get(i).getDateEnd());
			pojoEventResGetAll.setProductTypeId(product.get(i).getProductType().getId());
			pojoEventResGetAll.setCategoryId(product.get(i).getCategory().getId());
			pojoEventResGetAll.setVer(product.get(i).getVersion());
			pojoEventResGetAll.setIsActive(product.get(i).getIsActive());
			
			pojoResGet.add(pojoEventResGetAll);
		}

		return pojoResGet;
	}
}
