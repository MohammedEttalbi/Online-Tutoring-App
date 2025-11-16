package com.onlinetutoring.evaluationservice.service.Impl;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import com.onlinetutoring.evaluationservice.mapper.ReviewMapper;
import com.onlinetutoring.evaluationservice.repository.ReviewRepository;
import com.onlinetutoring.evaluationservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        existing.setReviewerName(review.getReviewerName());
        existing.setSubject(review.getSubject());
        existing.setDescription(review.getDescription());
        return reviewRepository.save(existing);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
