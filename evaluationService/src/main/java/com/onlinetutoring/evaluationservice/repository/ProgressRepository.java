package com.onlinetutoring.evaluationservice.repository;

import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}