package com.onlinetutoring.evaluationservice.service;

import com.onlinetutoring.evaluationservice.domain.entity.Progress;

import java.util.List;

public interface ProgressService {
    Progress createProgress(Progress progress);
    Progress updateProgress(Long id, Progress progress);
    void deleteProgress(Long id);
    Progress getProgressById(Long id);
    List<Progress> getAllProgresses();
}

