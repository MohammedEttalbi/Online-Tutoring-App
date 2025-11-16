package com.onlinetutoring.evaluationservice.service.Impl;

import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import com.onlinetutoring.evaluationservice.repository.ProgressRepository;
import com.onlinetutoring.evaluationservice.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;

    @Override
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress updateProgress(Long id, Progress progress) {
        Progress existing = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
        existing.setGoal(progress.getGoal());
        existing.setStatus(progress.getStatus());
        return progressRepository.save(existing);
    }

    @Override
    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }

    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    @Override
    public List<Progress> getAllProgresses() {
        return progressRepository.findAll();
    }
}
