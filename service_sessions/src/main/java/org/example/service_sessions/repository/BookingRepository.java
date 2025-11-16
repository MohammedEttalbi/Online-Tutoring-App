package org.example.service_sessions.repository;

import org.example.service_sessions.domain.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface BookingRepository extends JpaRepository<Booking, Long> {

    Page<Booking> findAllBySession_Id(Long sessionId, Pageable pageable);

    long countBySession_Id(Long sessionId);

    Page<Booking> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    long countBySession_IdAndDateTimeBetween(Long sessionId, LocalDateTime start, LocalDateTime end);

    boolean existsBySession_IdAndDateTime(Long sessionId, LocalDateTime dateTime);

    void deleteAllBySession_Id(Long sessionId);
}

