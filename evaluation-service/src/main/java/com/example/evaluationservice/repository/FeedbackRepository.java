package com.example.evaluationservice.repository;


import com.example.evaluationservice.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // récupérer les feedback d’un avis
    List<Feedback> findByReviewId(Long reviewId);
}
