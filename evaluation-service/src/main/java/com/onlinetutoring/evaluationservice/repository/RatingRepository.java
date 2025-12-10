package com.onlinetutoring.evaluationservice.repository;

import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // récupérer rating depuis l'id du review
    Rating findByReviewId(Long reviewId);
}

