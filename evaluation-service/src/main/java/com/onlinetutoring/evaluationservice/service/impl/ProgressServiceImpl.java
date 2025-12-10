package com.onlinetutoring.evaluationservice.service.impl;

import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import com.onlinetutoring.evaluationservice.exception.ResourceNotFoundException;
import com.onlinetutoring.evaluationservice.feignrequests.ResilientFeignRequests;
import com.onlinetutoring.evaluationservice.feignrequests.StudentDto;
import com.onlinetutoring.evaluationservice.feignrequests.TutorDto;
import com.onlinetutoring.evaluationservice.mapper.ProgressMapper;
import com.onlinetutoring.evaluationservice.repository.ProgressRepository;
import com.onlinetutoring.evaluationservice.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private final ResilientFeignRequests resilientFeignRequests;

    @Override
    public ProgressReadDTO createProgress(ProgressCreateUpdateDTO dto) {
        // Validate student exists
        validateStudentExists(dto.getStudentId());
        // Validate tutor exists
        validateTutorExists(dto.getTutorId());

        Progress progress = progressMapper.toEntity(dto);
        Progress saved = progressRepository.save(progress);
        return progressMapper.toReadDto(saved);
    }

    @Override
    public ProgressReadDTO getProgressById(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found with id " + id));
        return progressMapper.toReadDto(progress);
    }

    @Override
    public List<ProgressReadDTO> getProgressByStudentId(Long studentId) {
        return progressRepository.findByStudentId(studentId)
                .stream()
                .map(progressMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgressReadDTO> getProgressByStudentAndTutor(Long studentId, Long tutorId) {
        return progressRepository.findByStudentIdAndTutorId(studentId, tutorId)
                .stream()
                .map(progressMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgressReadDTO> getAllProgress() {
        return progressRepository.findAll()
                .stream()
                .map(progressMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgressReadDTO updateProgress(Long id, ProgressCreateUpdateDTO dto) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found with id " + id));

        // Validate student exists if being updated
        if (dto.getStudentId() != null) {
            validateStudentExists(dto.getStudentId());
        }
        // Validate tutor exists if being updated
        if (dto.getTutorId() != null) {
            validateTutorExists(dto.getTutorId());
        }

        progressMapper.partialUpdate(dto, progress);
        Progress updated = progressRepository.save(progress);
        return progressMapper.toReadDto(updated);
    }

    @Override
    public void deleteProgress(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found with id " + id));
        progressRepository.delete(progress);
    }

    // ========== Validation Helper Methods ==========

    private void validateStudentExists(Long studentId) {
        ResponseEntity<StudentDto> response = resilientFeignRequests.getStudentById(studentId);
        if (response == null || response.getBody() == null) {
            throw new ResourceNotFoundException("Student not found with id " + studentId);
        }
    }

    private void validateTutorExists(Long tutorId) {
        ResponseEntity<TutorDto> response = resilientFeignRequests.getTutorById(tutorId);
        if (response == null || response.getBody() == null) {
            throw new ResourceNotFoundException("Tutor not found with id " + tutorId);
        }
    }
}
