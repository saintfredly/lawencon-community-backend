package com.lawencon.community.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProductDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Product;
import com.lawencon.community.model.ProductType;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.product.PojoProductReq;
import com.lawencon.community.util.GenerateId;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final FileDao fileDao;

	public ProductService(ProductDao productDao, FileDao fileDao) {
		this.productDao = productDao;
		this.fileDao = fileDao;
	}

	public PojoRes save(PojoProductReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);

		Product product = new Product();
		product.setProductCode(generateCode);
		product.setProductName(data.getProductName());
		product.setProductDescription(data.getProductDescription());
		product.setProductLocation(data.getProductLocation());

		if (data.getPhoto() != null) {
			final File file = new File();
			file.setFileName(data.getPhoto().getFileName());
			file.setFileContent(data.getPhoto().getFileContent());
			file.setFileExtension(data.getPhoto().getFileExtension());
			file.setIsActive(true);

			fileDao.save(file);

			product.setFile(file);
		}
		
		product.setProviderName(data.getProviderName());
		product.setPrice(data.getPrice());
		product.setDateStart(data.getDateStart());
		product.setDateEnd(data.getDateEnd());
		
//		final Category category = category
		
//		product.setCategory(category);

		final ProductType productType = productDao.getByIdRef(ProductType.class, data.getProductTypeId());

		product.setProductType(productType);

		productDao.save(product);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}
}
