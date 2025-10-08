package com.onlinetutoring.usersservice.repository;

import com.onlinetutoring.usersservice.domain.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}