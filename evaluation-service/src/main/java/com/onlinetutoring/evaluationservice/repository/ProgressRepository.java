package com.onlinetutoring.evaluationservice.repository;


import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    // progression d’un étudiant avec un tuteur
    List<Progress> findByStudentIdAndTutorId(Long studentId, Long tutorId);

    // récupérer toute la progression d’un étudiant
    List<Progress> findByStudentId(Long studentId);
}

