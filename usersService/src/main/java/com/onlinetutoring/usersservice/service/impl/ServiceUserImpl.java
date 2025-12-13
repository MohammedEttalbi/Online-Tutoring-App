package com.onlinetutoring.usersservice.service.impl;

import com.onlinetutoring.usersservice.domain.entity.Subject;
import com.onlinetutoring.usersservice.domain.entity.User;
import com.onlinetutoring.usersservice.repository.SubjectRepository;
import com.onlinetutoring.usersservice.repository.UserRepository;
import com.onlinetutoring.usersservice.service.IServiceUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceUserImpl implements IServiceUser {

    private static final String USER_NOT_FOUND = "User not found: ";
    private static final String SUBJECT_NOT_FOUND = "Subject not found: ";

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id is required for update");
        }

        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + user.getId()));

        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        existing.setRole(user.getRole());

        return existing;
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(USER_NOT_FOUND + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User assignSubject(Long userId, Long subjectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + userId));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(SUBJECT_NOT_FOUND + subjectId));

        user.getSubjects().add(subject); // owning side is User (has @JoinTable)
        return user; // managed; changes flushed on tx commit
    }

    @Override
    public User removeSubject(Long userId, Long subjectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + userId));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(SUBJECT_NOT_FOUND + subjectId));

        user.getSubjects().remove(subject);
        return user;
    }

}
