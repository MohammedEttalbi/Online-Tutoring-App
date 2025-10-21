package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Student;

import java.util.List;

public interface IServiceStudent {
    Student createStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Student student);
    void deleteStudent(Long id);
}
