package com.onlinetutoring.service_sessions.controller;


import com.onlinetutoring.service_sessions.domain.dto.booking.BookingCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.booking.BookingDeleteDto;
import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.dto.booking.BookingUpdateDto;
import com.onlinetutoring.service_sessions.service.impl.ServiceBookingImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final ServiceBookingImpl serviceBookingImpl;

    public BookingController(ServiceBookingImpl serviceBookingImpl) {
        this.serviceBookingImpl = serviceBookingImpl;
    }

    /**
     * Get all bookings
     * GET /api/bookings
     */
    @GetMapping
    public ResponseEntity<List<BookingReadDto>> getAllBookings() {
        List<BookingReadDto> bookings = serviceBookingImpl.listAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Get booking by ID
     * GET /api/bookings/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookingReadDto> getBookingById(@PathVariable Long id) {
        BookingReadDto booking = serviceBookingImpl.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    /**
     * Get bookings by session ID
     * GET /api/bookings/session/{sessionId}
     */
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<BookingReadDto>> getBookingsBySessionId(@PathVariable Long sessionId) {
        List<BookingReadDto> bookings = serviceBookingImpl.getBookingsBySessionId(sessionId);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Get bookings by student name
     * GET /api/bookings/student?name={studentName}
     */
    @GetMapping("/student")
    public ResponseEntity<List<BookingReadDto>> getBookingsByStudentName(@RequestParam String name) {
        List<BookingReadDto> bookings = serviceBookingImpl.getBookingsByStudentName(name);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Create a new booking
     * POST /api/bookings
     */
    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingCreateDto bookingCreateDto) {
        return serviceBookingImpl.createBooking(bookingCreateDto);
    }

    /**
     * Update an existing booking
     * PUT /api/bookings
     */
    @PutMapping
    public ResponseEntity<?> updateBooking(@Valid @RequestBody BookingUpdateDto bookingUpdateDto) {
        return serviceBookingImpl.updateBooking(bookingUpdateDto);
    }

    /**
     * Delete a booking
     * DELETE /api/bookings
     */
    @DeleteMapping
    public ResponseEntity<?> deleteBooking(@Valid @RequestBody BookingDeleteDto bookingDeleteDto) {
        return serviceBookingImpl.deleteBooking(bookingDeleteDto);
    }

    /**
     * Alternative: Delete a booking by ID (RESTful style)
     * DELETE /api/bookings/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingById(@PathVariable Long id) {
        BookingDeleteDto deleteDto = new BookingDeleteDto();
        deleteDto.setId(id);
        return serviceBookingImpl.deleteBooking(deleteDto);
    }
}
