package org.example.service_sessions.service.impl;

import org.example.service_sessions.common.exception.NotFoundException;
import org.example.service_sessions.domain.dto.session.*;
import org.example.service_sessions.domain.entity.Material;
import org.example.service_sessions.domain.entity.Schedule;
import org.example.service_sessions.domain.entity.Session;
import org.example.service_sessions.mapper.SessionMapper;
import org.example.service_sessions.repository.MaterialRepository;
import org.example.service_sessions.repository.ScheduleRepository;
import org.example.service_sessions.repository.SessionRepository;
import org.example.service_sessions.service.IServiceSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ServiceSessionImpl implements IServiceSession {

    private final SessionRepository sessionRepository;
    private final ScheduleRepository scheduleRepository;
    private final MaterialRepository materialRepository;
    private final SessionMapper sessionMapper;


    public ServiceSessionImpl(SessionRepository sessionRepository,
                              ScheduleRepository scheduleRepository,
                              MaterialRepository materialRepository,
                              SessionMapper sessionMapper) {

        this.sessionRepository = sessionRepository;
        this.scheduleRepository = scheduleRepository;
        this.materialRepository = materialRepository;
        this.sessionMapper = sessionMapper;
    }


    @Override
    public SessionReadDto create(SessionCreateDto dto) {

        if (sessionRepository.existsByName(dto.name)) {

            throw new DataIntegrityViolationException("Session name already exists: " + dto.name);
        }

        // Validate schedule if provided
        if (dto.scheduleId != null && !scheduleRepository.existsById(dto.scheduleId)) {

            throw new NotFoundException("Schedule not found: id=" + dto.scheduleId);
        }

        // Validate materials if provided (ensure they exist)
        if (dto.materialIds != null && !dto.materialIds.isEmpty()) {

            long existing = materialRepository.countByIdIn(dto.materialIds);

            if (existing != dto.materialIds.size()) {

                throw new NotFoundException("One or more materials not found by ids: " + dto.materialIds);
            }
        }

        Session entity = sessionMapper.toEntity(dto);
        Session saved = sessionRepository.save(entity);

        return sessionMapper.toReadDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public SessionReadDto getById(Long id) {

        Session s = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found: id=" + id));

        return sessionMapper.toReadDto(s);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SessionReadDto> getAll(Pageable pageable) {

        return sessionRepository.findAll(pageable).map(sessionMapper::toReadDto);
    }


    @Override
    public SessionReadDto update(SessionUpdateDto dto) {

        Session entity = sessionRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Session not found: id=" + dto.getId()));

        if (dto.getName() != null && !dto.getName().equals(entity.getName())) {

            if (sessionRepository.existsByName(dto.getName())) {

                throw new DataIntegrityViolationException("Session name already exists: " + dto.getName());
            }
        }

        if (dto.getScheduleId() != null && !scheduleRepository.existsById(dto.getScheduleId())) {

            throw new NotFoundException("Schedule not found: id=" + dto.getScheduleId());
        }

        if (dto.getMaterialIds() != null && !dto.getMaterialIds().isEmpty()) {

            long existing = materialRepository.countByIdIn(dto.getMaterialIds());

            if (existing != dto.getMaterialIds().size()) {

                throw new NotFoundException("One or more materials not found by ids: " + dto.getMaterialIds());
            }
        }

        sessionMapper.update(dto, entity);
        Session saved = sessionRepository.save(entity);

        return sessionMapper.toReadDto(saved);
    }


    @Override
    public void delete(SessionDeleteDto dto) {

        Session entity = sessionRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Session not found: id=" + dto.getId()));

        sessionRepository.delete(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SessionReadDto> findByName(String name) {

        return sessionRepository.findByName(name).map(sessionMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return sessionRepository.existsByName(name);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SessionReadDto> searchByName(String namePart, Pageable pageable) {

        return sessionRepository.findAllByNameContainingIgnoreCase(namePart, pageable)
                .map(sessionMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SessionReadDto> findByDurationBetween(double min, double max) {

        return sessionRepository.findAllByDurationBetween(min, max)
                .stream().map(sessionMapper::toReadDto).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SessionReadDto> listByMaterial(Long materialId, Pageable pageable) {

        return sessionRepository.findAllByMaterials_Id(materialId, pageable)
                .map(sessionMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SessionReadDto> findByScheduleId(Long scheduleId) {

        return sessionRepository.findBySchedule_Id(scheduleId)
                .map(sessionMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SessionReadDto> listByScheduleDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {

        return sessionRepository.findAllBySchedule_DateTimeBetween(start, end, pageable)
                .map(sessionMapper::toReadDto);
    }
}

