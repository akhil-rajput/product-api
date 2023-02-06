package com.nagarro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nagarro.model.Product;

public interface ProductRepo extends CrudRepository<Product, String> {

	@Query(value = "SELECT * FROM product where product_name like %:query% or product_code like %:query%  ", nativeQuery = true)
	public List<Product> findByProductNameOrProductCode(String query);

	@Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
	public int findCountOfProducts();
}
