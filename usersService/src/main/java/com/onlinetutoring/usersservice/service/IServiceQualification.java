package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.Qualification;

import java.util.List;

public interface IServiceQualification {
    Qualification createQualification(Qualification qualification);
    Qualification getQualificationById(Long id);
    List<Qualification> getAllQualifications();
    Qualification updateQualification(Qualification qualification);
    void deleteQualification(Long id);

    List<Qualification> getQualificationsByTutorId(Long tutorId);
}