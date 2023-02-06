
package com.nagarro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dto.StatsDto;
import com.nagarro.service.ProductService;
import com.nagarro.service.ReviewService;
import com.nagarro.service.UserService;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class StatsController {
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<StatsDto> getStats() {

		int countProducts = productService.countProducts();
		StatsDto statsDto = new StatsDto();
		statsDto.setTotalProducts(countProducts);

		int countMembers = userService.countMembers();
		statsDto.setTotalMembers(countMembers);

		int countReviews = reviewService.countReviews();
		statsDto.setTotalReviews(countReviews);

		return ResponseEntity.of(Optional.of(statsDto));
	}

}
