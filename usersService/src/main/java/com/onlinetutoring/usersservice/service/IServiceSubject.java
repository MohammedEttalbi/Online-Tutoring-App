package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Subject;

import java.util.List;

public interface IServiceSubject {
    Subject createSubject(Subject subject);
    Subject getSubjectById(Long id);
    List<Subject> getAllSubjects();
    Subject updateSubject(Subject subject);
    void deleteSubject(Long id);

}
