package org.example.service_sessions.service;

import org.example.service_sessions.domain.dto.session.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface IServiceSession {

    SessionReadDto create(SessionCreateDto dto);

    SessionReadDto getById(Long id);

    Page<SessionReadDto> getAll(Pageable pageable);

    SessionReadDto update(SessionUpdateDto dto);

    void delete(SessionDeleteDto dto);

    Optional<SessionReadDto> findByName(String name);

    boolean existsByName(String name);

    Page<SessionReadDto> searchByName(String namePart, Pageable pageable);

    List<SessionReadDto> findByDurationBetween(double min, double max);

    Page<SessionReadDto> listByMaterial(Long materialId, Pageable pageable);

    Optional<SessionReadDto> findByScheduleId(Long scheduleId);

    Page<SessionReadDto> listByScheduleDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);
}

