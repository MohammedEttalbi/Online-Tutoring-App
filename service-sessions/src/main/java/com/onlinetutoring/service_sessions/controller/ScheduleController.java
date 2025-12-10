package com.onlinetutoring.service_sessions.controller;

import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleDeleteDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleReadDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleUpdateDto;
import com.onlinetutoring.service_sessions.service.impl.ServiceScheduleImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ServiceScheduleImpl serviceScheduleImpl;

    public ScheduleController(ServiceScheduleImpl serviceScheduleImpl) {
        this.serviceScheduleImpl = serviceScheduleImpl;
    }

    /**
     * Get all schedules (paginated)
     * GET /schedules
     */
    @GetMapping
    public ResponseEntity<Page<ScheduleReadDto>> getAllSchedules(
            @PageableDefault(size = 20) Pageable pageable) {
        Page<ScheduleReadDto> schedules = serviceScheduleImpl.getAll(pageable);
        return ResponseEntity.ok(schedules);
    }

    /**
     * Get schedule by ID
     * GET /schedules/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleReadDto> getScheduleById(@PathVariable Long id) {
        ScheduleReadDto schedule = serviceScheduleImpl.getById(id);
        return ResponseEntity.ok(schedule);
    }

    /**
     * Get schedule by session ID
     * GET /schedules/session/{sessionId}
     */
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<ScheduleReadDto> getScheduleBySessionId(@PathVariable Long sessionId) {
        return serviceScheduleImpl.findBySessionId(sessionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get schedules by date range
     * GET /schedules/range?start=...&end=...
     */
    @GetMapping("/range")
    public ResponseEntity<Page<ScheduleReadDto>> getSchedulesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<ScheduleReadDto> schedules = serviceScheduleImpl.listByDateRange(start, end, pageable);
        return ResponseEntity.ok(schedules);
    }

    /**
     * Check if schedule exists at given date/time
     * GET /schedules/exists?dateTime=...
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkScheduleExists(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        boolean exists = serviceScheduleImpl.existsAt(dateTime);
        return ResponseEntity.ok(exists);
    }

    /**
     * Create a new schedule
     * POST /schedules
     */
    @PostMapping
    public ResponseEntity<ScheduleReadDto> createSchedule(@Valid @RequestBody ScheduleCreateDto scheduleCreateDto) {
        ScheduleReadDto created = serviceScheduleImpl.create(scheduleCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update an existing schedule
     * PUT /schedules
     */
    @PutMapping
    public ResponseEntity<ScheduleReadDto> updateSchedule(@Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        ScheduleReadDto updated = serviceScheduleImpl.update(scheduleUpdateDto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a schedule
     * DELETE /schedules
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedule(@Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto) {
        serviceScheduleImpl.delete(scheduleDeleteDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Alternative: Delete a schedule by ID (RESTful style)
     * DELETE /schedules/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id) {
        ScheduleDeleteDto deleteDto = new ScheduleDeleteDto();
        deleteDto.setId(id);
        serviceScheduleImpl.delete(deleteDto);
        return ResponseEntity.noContent().build();
    }
}
