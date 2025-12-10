package com.onlinetutoring.evaluationservice.repository;


import com.onlinetutoring.evaluationservice.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // récupérer tous les avis d’un tuteur
    List<Review> findByTutorId(Long tutorId);

    // récupérer tous les avis d’un étudiant
    List<Review> findByStudentId(Long studentId);
}
