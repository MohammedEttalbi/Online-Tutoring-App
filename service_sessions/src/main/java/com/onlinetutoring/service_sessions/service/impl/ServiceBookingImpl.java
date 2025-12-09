package com.onlinetutoring.service_sessions.service.impl;

import com.onlinetutoring.service_sessions.domain.dto.booking.*;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import com.onlinetutoring.service_sessions.domain.entity.Session;
import com.onlinetutoring.service_sessions.repository.BookingRepository;
import com.onlinetutoring.service_sessions.repository.SessionRepository;
import com.onlinetutoring.service_sessions.feignrequests.ResilientFeignRequests;
import com.onlinetutoring.service_sessions.feignrequests.StudentDto;
import com.onlinetutoring.service_sessions.mapper.BookingMapper;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceBookingImpl {

    private final BookingRepository bookingRepository;
    private final SessionRepository sessionRepository;
    private final BookingMapper bookingMapper;
    private final ResilientFeignRequests resilientFeignRequests;

    public ServiceBookingImpl(BookingRepository bookingRepository,
            SessionRepository sessionRepository,
            BookingMapper bookingMapper,
            ResilientFeignRequests resilientFeignRequests) {
        this.bookingRepository = bookingRepository;
        this.sessionRepository = sessionRepository;
        this.bookingMapper = bookingMapper;
        this.resilientFeignRequests = resilientFeignRequests;
    }

    public List<BookingReadDto> listAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public BookingReadDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return bookingMapper.toReadDto(booking);
    }

    @Transactional
    public ResponseEntity<?> createBooking(BookingCreateDto bookingCreateDto) {

        if (bookingCreateDto == null || bookingCreateDto.getSessionId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid booking data");
        }

        try {
            // 1. Validate Session exists
            Session session = sessionRepository.findById(bookingCreateDto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found: " + bookingCreateDto.getSessionId()));

            // 2. Validate Student exists via users-service
            ResponseEntity<StudentDto> studentResponse = resilientFeignRequests
                    .getStudentById(bookingCreateDto.getStudentId());

            if (!studentResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Student not found: " + bookingCreateDto.getStudentId());
            }

            StudentDto student = studentResponse.getBody();
            if (student == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Student not found: " + bookingCreateDto.getStudentId());
            }

            // 3. Create the booking with student's full name
            Booking booking = bookingMapper.toEntity(bookingCreateDto);
            booking.setSession(session);
            booking.setStudentId(bookingCreateDto.getStudentId());

            Booking savedBooking = bookingRepository.save(booking);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookingMapper.toReadDto(savedBooking));

        } catch (FeignException feignEx) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Users service is currently unavailable");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating booking: " + ex.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> updateBooking(BookingUpdateDto bookingUpdateDto) {

        if (bookingUpdateDto == null || bookingUpdateDto.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid booking data");
        }

        try {
            Booking existingBooking = bookingRepository.findById(bookingUpdateDto.getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingUpdateDto.getId()));

            // Validate and update student if studentId is provided
            if (bookingUpdateDto.getStudentId() != null) {
                ResponseEntity<StudentDto> studentResponse = resilientFeignRequests
                        .getStudentById(bookingUpdateDto.getStudentId());

                if (!studentResponse.getStatusCode().is2xxSuccessful() || studentResponse.getBody() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Student not found: " + bookingUpdateDto.getStudentId());
                }

                StudentDto student = studentResponse.getBody();
                existingBooking.setStudentId(bookingUpdateDto.getStudentId());
            }

            // Use mapper to update other fields (dateTime, sessionId)
            bookingMapper.update(bookingUpdateDto, existingBooking);

            Booking updatedBooking = bookingRepository.save(existingBooking);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingMapper.toReadDto(updatedBooking));

        } catch (FeignException feignEx) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Users service is currently unavailable");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating booking: " + ex.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> deleteBooking(BookingDeleteDto bookingDeleteDto) {

        if (bookingDeleteDto == null || bookingDeleteDto.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid booking ID");
        }

        try {
            Booking booking = bookingRepository.findById(bookingDeleteDto.getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingDeleteDto.getId()));

            bookingRepository.delete(booking);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Booking deleted successfully");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting booking: " + ex.getMessage());
        }
    }

    public List<BookingReadDto> getBookingsBySessionId(Long sessionId) {
        return bookingRepository.findBySessionId(sessionId)
                .stream()
                .map(bookingMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public List<BookingReadDto> getBookingsByStudentId(Long studentId) {
        return bookingRepository.findByStudentId(studentId)
                .stream()
                .map(bookingMapper::toReadDto)
                .collect(Collectors.toList());
    }
}