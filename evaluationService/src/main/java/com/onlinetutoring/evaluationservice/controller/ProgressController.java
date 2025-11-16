package com.onlinetutoring.evaluationservice.controller;

import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import com.onlinetutoring.evaluationservice.mapper.ProgressMapper;
import com.onlinetutoring.evaluationservice.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;
    private final ProgressMapper progressMapper;

    @GetMapping
    public List<ProgressReadDto> getAll() {
        return progressService.getAllProgresses()
                .stream()
                .map(progressMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProgressReadDto getOne(@PathVariable Long id) {
        return progressMapper.toReadDto(progressService.getProgressById(id));
    }

    @PostMapping
    public ProgressReadDto create(@RequestBody ProgressCreateUpdateDto dto) {
        Progress progress = progressMapper.toEntity(dto);
        Progress saved = progressService.createProgress(progress);
        return progressMapper.toReadDto(saved);
    }

    @PutMapping("/{id}")
    public ProgressReadDto update(@PathVariable Long id, @RequestBody ProgressCreateUpdateDto dto) {
        Progress progress = progressMapper.toEntity(dto);
        Progress updated = progressService.updateProgress(id, progress);
        return progressMapper.toReadDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        progressService.deleteProgress(id);
    }
}

