package com.onlinetutoring.usersservice.service;

import com.onlinetutoring.usersservice.domain.entity.User;

import java.util.List;

public interface IServiceUser {
    User create(User user);
    User getById(Long id);
    List<User> getAll();
    User update(User user);
    void delete(Long id);

    User assignSubject(Long userId, Long subjectId);
    User removeSubject(Long userId, Long subjectId);


}

