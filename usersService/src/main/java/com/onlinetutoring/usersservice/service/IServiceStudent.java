package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Student;

import java.util.List;

public interface IServiceStudent {
    Student create(Student s);
    List<Student> findByLevel(String level);
}
