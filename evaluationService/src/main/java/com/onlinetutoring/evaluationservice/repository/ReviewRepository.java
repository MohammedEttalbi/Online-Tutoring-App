package com.onlinetutoring.evaluationservice.repository;

import com.onlinetutoring.evaluationservice.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}