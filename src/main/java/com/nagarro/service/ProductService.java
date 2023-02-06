package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.dao.ProductRepo;
import com.nagarro.model.Product;

@Component
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public Product getProduct(String productCode) {
		Product product = productRepo.findById(productCode).orElse(null);
		return product;
	}

	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	public List<Product> searchProducts(String query) {
		List<Product> products = productRepo.findByProductNameOrProductCode(query);
		return products;
	}

	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>) productRepo.findAll();
		return products;
	}

	public int countProducts() {
		int totalProducts = productRepo.findCountOfProducts();
		return totalProducts;
	}
}
