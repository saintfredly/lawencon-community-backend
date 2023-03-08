package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Product;
@Repository
public class ProductDao extends BaseMasterDao<Product>{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		final String sql = "SELECT * FROM t_product WHERE t_product.is_active=TRUE";
		final List<Product> product = ConnHandler.getManager().createNativeQuery(sql, Product.class).getResultList();
		return product;
	}


	@Override
	public Optional<Product> getById(String id) {
		final Product product = ConnHandler.getManager().find(Product.class, id);
		return Optional.ofNullable(product);
	}


	@Override
	public Optional<Product> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Product.class, id));
	}

}
