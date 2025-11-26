package com.onlinetutoring.usersservice.repository;

import com.onlinetutoring.usersservice.domain.entity.Tutor;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

}