package com.onlinetutoring.usersservice.repository;

import com.onlinetutoring.usersservice.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}