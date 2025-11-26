package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.student.StudentCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.student.StudentReadDto;
import com.onlinetutoring.usersservice.mapper.StudentMapper;
import com.onlinetutoring.usersservice.service.Impl.ServiceStudentImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final ServiceStudentImpl serviceStudent;
    private final StudentMapper studentMapper;

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<StudentReadDto>> getAllStudents() {
        List<StudentReadDto> students = serviceStudent.getAllStudents()
                .stream()
                .map(studentMapper::toReadDto)
                .toList();
        return ResponseEntity.ok(students);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<StudentReadDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentMapper.toReadDto(serviceStudent.getStudentById(id)));
    }

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<StudentReadDto> createStudent(@Valid @RequestBody StudentCreateUpdateDto dto) {
        return ResponseEntity.ok(
                studentMapper.toReadDto(serviceStudent.createStudent(studentMapper.toEntity(dto)))
        );
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<StudentReadDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentCreateUpdateDto dto
    ) {
        var existingStudent = serviceStudent.getStudentById(id);
        var updatedStudent = studentMapper.partialUpdate(dto, existingStudent);
        return ResponseEntity.ok(studentMapper.toReadDto(serviceStudent.updateStudent(updatedStudent)));
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            serviceStudent.deleteStudent(id);
            return ResponseEntity.ok("Student with ID " + id + " has been successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
