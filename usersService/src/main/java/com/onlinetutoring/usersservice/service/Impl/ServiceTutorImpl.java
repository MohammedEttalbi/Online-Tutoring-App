package com.onlinetutoring.usersservice.service.Impl;




import com.onlinetutoring.usersservice.domain.entity.Tutor;
import com.onlinetutoring.usersservice.repository.TutorRepository;
import com.onlinetutoring.usersservice.service.IServiceTutor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceTutorImpl implements IServiceTutor {

    private final TutorRepository tutorRepository;

    @Override
    public Tutor createTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    @Transactional(readOnly = true)
    public Tutor getTutorById(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tutor not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor updateTutor(Tutor tutor) {
        if (tutor.getId() == null) {
            throw new IllegalArgumentException("Tutor id is required for update");
        }

        Tutor existing = tutorRepository.findById(tutor.getId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor not found: " + tutor.getId()));

        existing.setFirstName(tutor.getFirstName());
        existing.setLastName(tutor.getLastName());
        existing.setEmail(tutor.getEmail());
        existing.setBio(tutor.getBio());
        existing.setRole(tutor.getRole());



        return existing;
    }

    @Override
    public void deleteTutor(Long id) {
        if (!tutorRepository.existsById(id)) {
            throw new EntityNotFoundException("Tutor not found: " + id);
        }
        tutorRepository.deleteById(id);
    }
}

