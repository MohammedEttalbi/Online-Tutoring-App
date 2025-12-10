package com.onlinetutoring.service_sessions.controller;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialReadDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionDeleteDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionReadDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionUpdateDto;
import com.onlinetutoring.service_sessions.service.impl.ServiceBookingImpl;
import com.onlinetutoring.service_sessions.service.impl.ServiceMaterialImpl;
import com.onlinetutoring.service_sessions.service.impl.ServiceSessionImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final ServiceSessionImpl serviceSessionImpl;
    private final ServiceBookingImpl serviceBookingImpl;
    private final ServiceMaterialImpl serviceMaterialImpl;

    public SessionController(ServiceSessionImpl serviceSessionImpl,
            ServiceBookingImpl serviceBookingImpl,
            ServiceMaterialImpl serviceMaterialImpl) {
        this.serviceSessionImpl = serviceSessionImpl;
        this.serviceBookingImpl = serviceBookingImpl;
        this.serviceMaterialImpl = serviceMaterialImpl;
    }

    /**
     * Get all sessions (paginated)
     * GET /sessions
     */
    @GetMapping
    public ResponseEntity<Page<SessionReadDto>> getAllSessions(
            @PageableDefault(size = 20) Pageable pageable) {
        Page<SessionReadDto> sessions = serviceSessionImpl.getAll(pageable);
        return ResponseEntity.ok(sessions);
    }

    /**
     * Get session by ID
     * GET /sessions/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SessionReadDto> getSessionById(@PathVariable Long id) {
        SessionReadDto session = serviceSessionImpl.getById(id);
        return ResponseEntity.ok(session);
    }

    /**
     * Search sessions by name
     * GET /sessions/search?name=...
     */
    @GetMapping("/search")
    public ResponseEntity<Page<SessionReadDto>> searchSessionsByName(
            @RequestParam String name,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<SessionReadDto> sessions = serviceSessionImpl.searchByName(name, pageable);
        return ResponseEntity.ok(sessions);
    }

    /**
     * Get sessions by duration range
     * GET /sessions/duration?min=...&max=...
     */
    @GetMapping("/duration")
    public ResponseEntity<List<SessionReadDto>> getSessionsByDuration(
            @RequestParam double min,
            @RequestParam double max) {
        List<SessionReadDto> sessions = serviceSessionImpl.findByDurationBetween(min, max);
        return ResponseEntity.ok(sessions);
    }

    /**
     * Get sessions by material ID
     * GET /sessions/material/{materialId}
     */
    @GetMapping("/material/{materialId}")
    public ResponseEntity<Page<SessionReadDto>> getSessionsByMaterial(
            @PathVariable Long materialId,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<SessionReadDto> sessions = serviceSessionImpl.listByMaterial(materialId, pageable);
        return ResponseEntity.ok(sessions);
    }

    /**
     * Get session by schedule ID
     * GET /sessions/schedule/{scheduleId}
     */
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<SessionReadDto> getSessionByScheduleId(@PathVariable Long scheduleId) {
        return serviceSessionImpl.findByScheduleId(scheduleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get sessions by schedule date range
     * GET /sessions/schedule/range?start=...&end=...
     */
    @GetMapping("/schedule/range")
    public ResponseEntity<Page<SessionReadDto>> getSessionsByScheduleDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<SessionReadDto> sessions = serviceSessionImpl.listByScheduleDateRange(start, end, pageable);
        return ResponseEntity.ok(sessions);
    }

    /**
     * Create a new session
     * POST /sessions
     */
    @PostMapping
    public ResponseEntity<SessionReadDto> createSession(@Valid @RequestBody SessionCreateDto sessionCreateDto) {
        SessionReadDto created = serviceSessionImpl.create(sessionCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update an existing session
     * PUT /sessions
     */
    @PutMapping
    public ResponseEntity<SessionReadDto> updateSession(@Valid @RequestBody SessionUpdateDto sessionUpdateDto) {
        SessionReadDto updated = serviceSessionImpl.update(sessionUpdateDto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a session
     * DELETE /sessions
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSession(@Valid @RequestBody SessionDeleteDto sessionDeleteDto) {
        serviceSessionImpl.delete(sessionDeleteDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Alternative: Delete a session by ID (RESTful style)
     * DELETE /sessions/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long id) {
        SessionDeleteDto deleteDto = new SessionDeleteDto();
        deleteDto.setId(id);
        serviceSessionImpl.delete(deleteDto);
        return ResponseEntity.noContent().build();
    }

    // ========== OneToMany/ManyToMany Relationship Endpoints ==========

    /**
     * Get all bookings for a session
     * GET /sessions/{id}/bookings
     */
    @GetMapping("/{id}/bookings")
    public ResponseEntity<List<BookingReadDto>> getBookingsBySession(@PathVariable Long id) {
        // First verify session exists
        serviceSessionImpl.getById(id);
        List<BookingReadDto> bookings = serviceBookingImpl.getBookingsBySessionId(id);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Get all materials for a session
     * GET /sessions/{id}/materials
     */
    @GetMapping("/{id}/materials")
    public ResponseEntity<Page<MaterialReadDto>> getMaterialsBySession(
            @PathVariable Long id,
            @PageableDefault(size = 20) Pageable pageable) {
        // First verify session exists
        serviceSessionImpl.getById(id);
        Page<MaterialReadDto> materials = serviceMaterialImpl.listBySession(id, pageable);
        return ResponseEntity.ok(materials);
    }
}
