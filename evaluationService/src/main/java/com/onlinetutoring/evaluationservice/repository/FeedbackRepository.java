package com.onlinetutoring.evaluationservice.repository;

import com.onlinetutoring.evaluationservice.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}