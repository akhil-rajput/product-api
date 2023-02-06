package com.nagarro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.dao.ReviewRepo;
import com.nagarro.dto.ReviewDto;
import com.nagarro.model.Review;

@Component
public class ReviewService {

	@Autowired
	ReviewRepo reviewRepo;

	public void saveReview(Review review) {
		reviewRepo.save(review);
	}

	public List<ReviewDto> getReviews(String productId) {

		String[] comments = reviewRepo.findReviewsByProductId(productId);
		List<ReviewDto> reviews = new ArrayList<>();

		for (String review : comments) {
			String[] str;
			str = review.split(",");
			ReviewDto reviewDto = new ReviewDto();
			reviewDto.setReview(str[0]);
			reviewDto.setUsename(str[1]);
			reviews.add(reviewDto);
		}
		return reviews;
	}

	public int countReviews() {
		int totalReviews = reviewRepo.countReviews();
		return totalReviews;
	}

}
