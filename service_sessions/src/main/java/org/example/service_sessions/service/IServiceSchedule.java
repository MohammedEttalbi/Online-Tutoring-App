package org.example.service_sessions.service;

import org.example.service_sessions.domain.dto.schedule.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;


public interface IServiceSchedule {

    ScheduleReadDto create(ScheduleCreateDto dto);

    ScheduleReadDto getById(Long id);

    Page<ScheduleReadDto> getAll(Pageable pageable);

    ScheduleReadDto update(ScheduleUpdateDto dto);

    void delete(ScheduleDeleteDto dto);

    Page<ScheduleReadDto> listByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    boolean existsAt(LocalDateTime dateTime);

    Optional<ScheduleReadDto> findBySessionId(Long sessionId);
}

