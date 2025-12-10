package com.onlinetutoring.evaluationservice.service;


import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewReadDTO;

import java.util.List;

public interface ReviewService {

    ReviewReadDTO createReview(ReviewCreateUpdateDTO dto);

    ReviewReadDTO getReviewById(Long id);

    List<ReviewReadDTO> getAllReviews();

    List<ReviewReadDTO> getReviewsByTutorId(Long tutorId);

    List<ReviewReadDTO> getReviewsByStudentId(Long studentId);

    ReviewReadDTO updateReview(Long id, ReviewCreateUpdateDTO dto);

    void deleteReview(Long id);
}
