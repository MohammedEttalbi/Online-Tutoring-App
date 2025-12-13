package com.onlinetutoring.usersservice.repository;

import com.onlinetutoring.usersservice.domain.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

}