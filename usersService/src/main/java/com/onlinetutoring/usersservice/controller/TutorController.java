package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.tutor.TutorReadDto;
import com.onlinetutoring.usersservice.mapper.TutorMapper;
import com.onlinetutoring.usersservice.service.Impl.ServiceTutorImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final ServiceTutorImpl serviceTutor;
    private final TutorMapper tutorMapper;

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<TutorReadDto>> getAllTutors() {
        List<TutorReadDto> tutors = serviceTutor.getAllTutors()
                .stream()
                .map(tutorMapper::toReadDto)
                .toList();
        return ResponseEntity.ok(tutors);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<TutorReadDto> getTutorById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorMapper.toReadDto(serviceTutor.getTutorById(id)));
    }

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<TutorReadDto> createTutor(@Valid @RequestBody TutorCreateUpdateDto dto) {
        return ResponseEntity.ok(
                tutorMapper.toReadDto(serviceTutor.createTutor(tutorMapper.toEntity(dto)))
        );
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<TutorReadDto> updateTutor(
            @PathVariable Long id,
            @Valid @RequestBody TutorCreateUpdateDto dto
    ) {
        var existingTutor = serviceTutor.getTutorById(id);
        var updatedTutor = tutorMapper.partialUpdate(dto, existingTutor);
        return ResponseEntity.ok(tutorMapper.toReadDto(serviceTutor.updateTutor(updatedTutor)));
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutor(@PathVariable Long id) {
        try {
            serviceTutor.deleteTutor(id);
            return ResponseEntity.ok("Tutor with ID " + id + " has been successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
