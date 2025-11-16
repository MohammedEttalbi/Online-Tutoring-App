package org.example.service_sessions.service;

import org.example.service_sessions.domain.dto.booking.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface IServiceBooking {

    BookingReadDto create(BookingCreateDto dto);

    BookingReadDto getById(Long id);

    Page<BookingReadDto> getAll(Pageable pageable);

    BookingReadDto update(BookingUpdateDto dto);

    void delete(BookingDeleteDto dto);

    Page<BookingReadDto> listBySession(Long sessionId, Pageable pageable);

    long countBySession(Long sessionId);

    Page<BookingReadDto> listByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    long countBySessionInRange(Long sessionId, LocalDateTime start, LocalDateTime end);

    boolean existsAtDateTime(Long sessionId, LocalDateTime dateTime);

    void deleteBySession(Long sessionId);
}

