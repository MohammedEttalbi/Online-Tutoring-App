package com.onlinetutoring.usersservice.service.Impl;

import com.onlinetutoring.usersservice.domain.entity.Qualification;
import com.onlinetutoring.usersservice.repository.QualificationRepository;
import com.onlinetutoring.usersservice.service.IServiceQualification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceQualificationImpl implements IServiceQualification {

    private final QualificationRepository qualificationRepository;

    @Override
    public Qualification createQualification(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    @Transactional(readOnly = true)
    public Qualification getQualificationById(Long id) {
        return qualificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Qualification not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    @Override
    public Qualification updateQualification(Qualification qualification) {
        if (qualification.getId() == null) {
            throw new IllegalArgumentException("Qualification id is required for update");
        }

        Qualification existing = qualificationRepository.findById(qualification.getId())
                .orElseThrow(() -> new EntityNotFoundException("Qualification not found: " + qualification.getId()));

        existing.setTitle(qualification.getTitle());
        existing.setInstitution(qualification.getInstitution());




        return existing;
    }

    @Override
    public void deleteQualification(Long id) {
        if (!qualificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Qualification not found: " + id);
        }
        qualificationRepository.deleteById(id);
    }
}
