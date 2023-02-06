package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

	@Query(value = "SELECT comment,user_id from review where product_id =:productId", nativeQuery = true)
	public String[] findReviewsByProductId(@Param("productId") String productId);

	@Query(value = "SELECT COUNT(*) FROM review", nativeQuery = true)
	public int countReviews();

}
