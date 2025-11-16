package com.onlinetutoring.evaluationservice.service;

import com.onlinetutoring.evaluationservice.domain.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
}

