package com.onlinetutoring.evaluationservice.repository;

import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}