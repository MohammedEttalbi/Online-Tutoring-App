package com.onlinetutoring.usersservice.service.Impl;

import com.onlinetutoring.usersservice.domain.entity.Student;
import com.onlinetutoring.usersservice.repository.StudentRepository;
import com.onlinetutoring.usersservice.service.IServiceStudent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceStudentImpl implements IServiceStudent {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        if (student.getId() == null) {
            throw new IllegalArgumentException("Student id is required for update");
        }

        Student existing = studentRepository.findById(student.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + student.getId()));

        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setEmail(student.getEmail());
        existing.setLevel(student.getLevel());
        existing.setRole(student.getRole());



        return existing;
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found: " + id);
        }
        studentRepository.deleteById(id);
    }
}
