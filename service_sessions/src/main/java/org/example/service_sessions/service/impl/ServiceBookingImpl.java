package org.example.service_sessions.service.impl;

import org.example.service_sessions.common.exception.NotFoundException;
import org.example.service_sessions.domain.dto.booking.*;
import org.example.service_sessions.domain.entity.Booking;
import org.example.service_sessions.domain.entity.Session;
import org.example.service_sessions.mapper.BookingMapper;
import org.example.service_sessions.repository.BookingRepository;
import org.example.service_sessions.repository.SessionRepository;
import org.example.service_sessions.service.IServiceBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
public class ServiceBookingImpl implements IServiceBooking {

    private final BookingRepository bookingRepository;
    private final SessionRepository sessionRepository;
    private final BookingMapper bookingMapper;


    public ServiceBookingImpl(BookingRepository bookingRepository,
                              SessionRepository sessionRepository,
                              BookingMapper bookingMapper) {

        this.bookingRepository = bookingRepository;
        this.sessionRepository = sessionRepository;
        this.bookingMapper = bookingMapper;
    }


    @Override
    public BookingReadDto create(BookingCreateDto dto) {

        // Ensure session exists
        Session session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new NotFoundException("Session not found: id=" + dto.getSessionId()));

        // Conflict check: a booking for same session at same time
        if (bookingRepository.existsBySession_IdAndDateTime(dto.getSessionId(), dto.getDateTime())) {
            throw new IllegalStateException("A booking already exists for this session at the specified date/time");
        }

        Booking entity = bookingMapper.toEntity(dto);

        // Ensure association is managed entity
        entity.setSession(session);

        Booking saved = bookingRepository.save(entity);

        return bookingMapper.toReadDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public BookingReadDto getById(Long id) {

        Booking b = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found: id=" + id));

        return bookingMapper.toReadDto(b);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<BookingReadDto> getAll(Pageable pageable) {

        return bookingRepository.findAll(pageable).map(bookingMapper::toReadDto);
    }


    @Override
    public BookingReadDto update(BookingUpdateDto dto) {

        Booking entity = bookingRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Booking not found: id=" + dto.getId()));

        // If session change requested, ensure session exists
        if (dto.getSessionId() != null) {

            Session session = sessionRepository.findById(dto.getSessionId())
                    .orElseThrow(() -> new NotFoundException("Session not found: id=" + dto.getSessionId()));
            entity.setSession(session);
        }

        // If date change (and/or session change), check conflict
        LocalDateTime newDate = dto.getDateTime() != null ? dto.getDateTime() : entity.getDateTime();

        Long newSessionId = dto.getSessionId() != null ? dto.getSessionId() : (entity.getSession() != null ? entity.getSession().getId() : null);

        if (newSessionId != null) {

            boolean conflict = bookingRepository.existsBySession_IdAndDateTime(newSessionId, newDate);

            if (conflict && !(newDate.equals(entity.getDateTime()) && newSessionId.equals(entity.getSession().getId()))) {

                throw new IllegalStateException("A booking already exists for this session at the specified date/time");
            }
        }

        bookingMapper.update(dto, entity);
        Booking saved = bookingRepository.save(entity);

        return bookingMapper.toReadDto(saved);
    }


    @Override
    public void delete(BookingDeleteDto dto) {

        Booking entity = bookingRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Booking not found: id=" + dto.getId()));

        bookingRepository.delete(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<BookingReadDto> listBySession(Long sessionId, Pageable pageable) {

        return bookingRepository.findAllBySession_Id(sessionId, pageable).map(bookingMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public long countBySession(Long sessionId) {
        return bookingRepository.countBySession_Id(sessionId);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<BookingReadDto> listByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {

        return bookingRepository.findAllByDateTimeBetween(start, end, pageable).map(bookingMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public long countBySessionInRange(Long sessionId, LocalDateTime start, LocalDateTime end) {

        return bookingRepository.countBySession_IdAndDateTimeBetween(sessionId, start, end);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsAtDateTime(Long sessionId, LocalDateTime dateTime) {

        return bookingRepository.existsBySession_IdAndDateTime(sessionId, dateTime);
    }


    @Override
    public void deleteBySession(Long sessionId) {
        bookingRepository.deleteAllBySession_Id(sessionId);
    }
}

