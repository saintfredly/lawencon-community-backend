package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Product;

@Repository
public class ProductDao extends BaseMasterDao<Product> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		final String sql = "SELECT * FROM t_product WHERE t_product.is_active=TRUE";
		final List<Product> product = ConnHandler.getManager().createNativeQuery(sql, Product.class).getResultList();
		return product;
	}

	@Override
	public Optional<Product> getById(String id) {
		return Optional.ofNullable(super.getById(Product.class, id));
	}

	@Override
	public Optional<Product> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Product.class, id));
	}
	
	public Optional<Product> getByIdRef(String id) {
		return Optional.ofNullable(super.getByIdRef(Product.class, id));
	} 

	@SuppressWarnings("unchecked")
	public List<Product> getAllByProductType(String code) {
		final String sql = "SELECT * FROM t_product WHERE t_product.product_type_id=(SELECT id FROM t_product_type WHERE t_product_type.product_type_code=:code) AND t_product.is_active=TRUE";
		final List<Product> product = ConnHandler.getManager().createNativeQuery(sql, Product.class)
				.setParameter("code", code).getResultList();
		return product;
	}
}
