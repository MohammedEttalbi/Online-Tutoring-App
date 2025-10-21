package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Tutor;

import java.util.List;

public interface IServiceTutor {
    Tutor createTutor(Tutor tutor);
    Tutor getTutorById(Long id);
    List<Tutor> getAllTutors();
    Tutor updateTutor(Tutor tutor);
    void deleteTutor(Long id);
}