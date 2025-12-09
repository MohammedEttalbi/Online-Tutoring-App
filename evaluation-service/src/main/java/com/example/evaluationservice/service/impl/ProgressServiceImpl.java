package com.example.evaluationservice.service.impl;


import com.example.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Progress.ProgressReadDTO;
import com.example.evaluationservice.domain.entity.Progress;
import com.example.evaluationservice.exception.ResourceNotFoundException;
import com.example.evaluationservice.mapper.ProgressMapper;
import com.example.evaluationservice.repository.ProgressRepository;
import com.example.evaluationservice.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;

    @Override
    public ProgressReadDTO createProgress(ProgressCreateUpdateDTO dto) {
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
}

