package com.onlinetutoring.evaluationservice.service;


import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDTO;

import java.util.List;

public interface ProgressService {

    ProgressReadDTO createProgress(ProgressCreateUpdateDTO dto);

    ProgressReadDTO getProgressById(Long id);

    List<ProgressReadDTO> getProgressByStudentId(Long studentId);

    List<ProgressReadDTO> getProgressByStudentAndTutor(Long studentId, Long tutorId);

    List<ProgressReadDTO> getAllProgress();

    ProgressReadDTO updateProgress(Long id, ProgressCreateUpdateDTO dto);

    void deleteProgress(Long id);
}

