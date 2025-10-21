package com.onlinetutoring.usersservice.service.Impl;

import com.onlinetutoring.usersservice.domain.entity.Subject;
import com.onlinetutoring.usersservice.repository.SubjectRepository;
import com.onlinetutoring.usersservice.service.IServiceSubject;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceSubjectImpl implements IServiceSubject {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Subject subject) {
        if (subject.getId() == null) {
            throw new IllegalArgumentException("Subject id is required for update");
        }

        Subject existing = subjectRepository.findById(subject.getId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found: " + subject.getId()));

        existing.setName(subject.getName());
        existing.setDescription(subject.getDescription());




        return existing;
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject not found: " + id);
        }
        subjectRepository.deleteById(id);
    }
}
