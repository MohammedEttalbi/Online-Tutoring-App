package com.onlinetutoring.evaluationservice.controller;

import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Feedback;
import com.onlinetutoring.evaluationservice.mapper.FeedbackMapper;
import com.onlinetutoring.evaluationservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    @GetMapping
    public List<FeedbackReadDto> getAll() {
        return feedbackService.getAllFeedbacks()
                .stream()
                .map(feedbackMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public FeedbackReadDto getOne(@PathVariable Long id) {
        return feedbackMapper.toReadDto(feedbackService.getFeedbackById(id));
    }

    @PostMapping
    public FeedbackReadDto create(@RequestBody FeedbackCreateUpdateDto dto) {
        Feedback feedback = feedbackMapper.toEntity(dto);
        Feedback saved = feedbackService.createFeedback(feedback);
        return feedbackMapper.toReadDto(saved);
    }

    @PutMapping("/{id}")
    public FeedbackReadDto update(@PathVariable Long id, @RequestBody FeedbackCreateUpdateDto dto) {
        Feedback feedback = feedbackMapper.toEntity(dto);
        Feedback updated = feedbackService.updateFeedback(id, feedback);
        return feedbackMapper.toReadDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
