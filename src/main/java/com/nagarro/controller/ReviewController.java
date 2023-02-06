package com.nagarro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dto.ReviewDto;
import com.nagarro.model.Product;
import com.nagarro.model.Review;
import com.nagarro.model.User;
import com.nagarro.service.ProductService;
import com.nagarro.service.ReviewService;
import com.nagarro.service.UserService;
import com.nagarro.util.UserUtil;

@RestController
@RequestMapping("/product-api/products/{productId}/reviews")
@CrossOrigin("*")
public class ReviewController {


	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<?> addReview(@PathVariable("productId") String id, @RequestBody Review review) {

		String username = UserUtil.getCurrentUser();
		User user = userService.getUser(username);
		Product product = productService.getProduct(id);
		review.setUser(user);
		review.setProduct(product);
		if (user.getUsername() != null && product.getProductCode() != null && review.getComment() != null) {
			reviewService.saveReview(review);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping
	public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("productId") String productId) {

		List<ReviewDto> reviews = reviewService.getReviews(productId);
		if (reviews.size() > 0) {
			return ResponseEntity.of(Optional.of(reviews));

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}