package com.onlinetutoring.service_sessions.repository;

import com.onlinetutoring.service_sessions.domain.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    boolean existsByDateTime(LocalDateTime dateTime);

    Optional<Schedule> findBySession_Id(Long sessionId);
}

