package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.mapper.SubjectMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceSubjectImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final ServiceSubjectImpl serviceSubject;
    private final SubjectMapper subjectMapper;

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<SubjectReadDto>> getAllSubjects() {
        List<SubjectReadDto> subjects = serviceSubject.getAllSubjects()
                .stream()
                .map(subjectMapper::toReadDto)
                .toList();
        return ResponseEntity.ok(subjects);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<SubjectReadDto> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectMapper.toReadDto(serviceSubject.getSubjectById(id)));
    }

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<SubjectReadDto> createSubject(@Valid @RequestBody SubjectCreateUpdateDto dto) {
        return ResponseEntity.ok(
                subjectMapper.toReadDto(serviceSubject.createSubject(subjectMapper.toEntity(dto))));
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<SubjectReadDto> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody SubjectCreateUpdateDto dto) {
        var existingSubject = serviceSubject.getSubjectById(id);
        var updatedSubject = subjectMapper.partialUpdate(dto, existingSubject);
        return ResponseEntity.ok(subjectMapper.toReadDto(serviceSubject.updateSubject(updatedSubject)));
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        try {
            serviceSubject.deleteSubject(id);
            return ResponseEntity.ok("Subject with ID " + id + " has been successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
