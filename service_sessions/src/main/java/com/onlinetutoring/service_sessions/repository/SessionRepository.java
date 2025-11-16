package com.onlinetutoring.service_sessions.repository;

import com.onlinetutoring.service_sessions.domain.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByName(String name);

    boolean existsByName(String name);

    Page<Session> findAllByNameContainingIgnoreCase(String namePart, Pageable pageable);

    java.util.List<Session> findAllByDurationBetween(double min, double max);

    Page<Session> findAllByMaterials_Id(Long materialId, Pageable pageable);

    Optional<Session> findBySchedule_Id(Long scheduleId);

    Page<Session> findAllBySchedule_DateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}

