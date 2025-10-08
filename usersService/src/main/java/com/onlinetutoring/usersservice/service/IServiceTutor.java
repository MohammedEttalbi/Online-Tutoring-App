package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Tutor;

import java.util.List;

public interface IServiceTutor {
    Tutor create(Tutor t);
    List<Tutor> findByMinRating(double min);
}