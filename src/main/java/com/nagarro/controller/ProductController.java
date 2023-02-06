package com.nagarro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.model.Product;
import com.nagarro.service.ProductService;

@RestController
@RequestMapping("/product-api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false, name = "query") String query) {

		List<Product> products;
		if (query == null) {
			products = productService.getAllProducts();

		} else {
			products = productService.searchProducts(query);
		}
		return ResponseEntity.of(Optional.of(products));
	}

	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody Product product) {

		if (product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(productService.addProduct(product));
	}
}