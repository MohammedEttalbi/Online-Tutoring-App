package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.mapper.QualificationMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceQualificationImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
@RequiredArgsConstructor
public class QualificationController {

    private final ServiceQualificationImpl serviceQualification;
    private final QualificationMapper qualificationMapper;

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<QualificationReadDto>> getAllQualifications() {
        List<QualificationReadDto> qualifications = serviceQualification.getAllQualifications()
                .stream()
                .map(qualificationMapper::toReadDto)
                .toList();
        return ResponseEntity.ok(qualifications);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<QualificationReadDto> getQualificationById(@PathVariable Long id) {
        return ResponseEntity.ok(qualificationMapper.toReadDto(serviceQualification.getQualificationById(id)));
    }

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<QualificationReadDto> createQualification(
            @Valid @RequestBody QualificationCreateUpdateDto dto) {
        return ResponseEntity.ok(
                qualificationMapper
                        .toReadDto(serviceQualification.createQualification(qualificationMapper.toEntity(dto))));
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<QualificationReadDto> updateQualification(
            @PathVariable Long id,
            @Valid @RequestBody QualificationCreateUpdateDto dto) {
        var existingQualification = serviceQualification.getQualificationById(id);
        var updatedQualification = qualificationMapper.partialUpdate(dto, existingQualification);
        return ResponseEntity
                .ok(qualificationMapper.toReadDto(serviceQualification.updateQualification(updatedQualification)));
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQualification(@PathVariable Long id) {
        try {
            serviceQualification.deleteQualification(id);
            return ResponseEntity.ok("Qualification with ID " + id + " has been successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
