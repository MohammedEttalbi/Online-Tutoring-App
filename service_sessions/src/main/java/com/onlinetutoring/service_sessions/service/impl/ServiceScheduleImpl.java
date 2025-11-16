package com.onlinetutoring.service_sessions.service.impl;

import com.onlinetutoring.service_sessions.common.exception.NotFoundException;
import com.onlinetutoring.service_sessions.domain.dto.schedule.*;
import com.onlinetutoring.service_sessions.domain.entity.Schedule;
import com.onlinetutoring.service_sessions.mapper.ScheduleMapper;
import com.onlinetutoring.service_sessions.repository.ScheduleRepository;
import com.onlinetutoring.service_sessions.service.IServiceSchedule;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
public class ServiceScheduleImpl implements IServiceSchedule {


    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;


    public ServiceScheduleImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {

        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }


    @Override
    public ScheduleReadDto create(ScheduleCreateDto dto) {

        if (scheduleRepository.existsByDateTime(dto.getDateTime())) {

            throw new DataIntegrityViolationException("A schedule already exists at this date/time");
        }

        Schedule entity = scheduleMapper.toEntity(dto);
        Schedule saved = scheduleRepository.save(entity);

        return scheduleMapper.toReadDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public ScheduleReadDto getById(Long id) {

        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found: id=" + id));

        return scheduleMapper.toReadDto(s);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleReadDto> getAll(Pageable pageable) {

        return scheduleRepository.findAll(pageable).map(scheduleMapper::toReadDto);
    }


    @Override
    public ScheduleReadDto update(ScheduleUpdateDto dto) {

        Schedule entity = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Schedule not found: id=" + dto.getId()));

        if (dto.getDateTime() != null && !dto.getDateTime().equals(entity.getDateTime())) {

            if (scheduleRepository.existsByDateTime(dto.getDateTime())) {

                throw new DataIntegrityViolationException("A schedule already exists at this date/time");
            }
        }

        scheduleMapper.update(dto, entity);
        Schedule saved = scheduleRepository.save(entity);

        return scheduleMapper.toReadDto(saved);
    }


    @Override
    public void delete(ScheduleDeleteDto dto) {

        Schedule entity = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Schedule not found: id=" + dto.getId()));

        scheduleRepository.delete(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleReadDto> listByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {

        return scheduleRepository.findAllByDateTimeBetween(start, end, pageable)
                .map(scheduleMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsAt(LocalDateTime dateTime) {
        return scheduleRepository.existsByDateTime(dateTime);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ScheduleReadDto> findBySessionId(Long sessionId) {

        return scheduleRepository.findBySession_Id(sessionId).map(scheduleMapper::toReadDto);
    }
}

