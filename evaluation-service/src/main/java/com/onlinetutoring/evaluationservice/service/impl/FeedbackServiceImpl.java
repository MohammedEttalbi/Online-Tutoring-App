package com.onlinetutoring.evaluationservice.service.impl;


import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Feedback;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import com.onlinetutoring.evaluationservice.exception.ResourceNotFoundException;
import com.onlinetutoring.evaluationservice.mapper.FeedbackMapper;
import com.onlinetutoring.evaluationservice.repository.FeedbackRepository;
import com.onlinetutoring.evaluationservice.repository.ReviewRepository;
import com.onlinetutoring.evaluationservice.service.FeedbackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReviewRepository reviewRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    @Transactional
    public FeedbackReadDTO createFeedback(FeedbackCreateUpdateDTO dto) {

        // Convertir DTO → Entité
        Feedback feedback = feedbackMapper.toEntity(dto);

        // Charger la review
        Review review = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review not found with id " + dto.getReviewId()));

        // Associer feedback ↔ review
        feedback.setReview(review);
        feedback.setCreatedAt(LocalDateTime.now());

        Feedback saved = feedbackRepository.save(feedback);

        return feedbackMapper.toReadDto(saved);
    }


    @Override
    public FeedbackReadDTO getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id " + id));
        return feedbackMapper.toReadDto(feedback);
    }

    @Override
    public List<FeedbackReadDTO> getFeedbacksByReviewId(Long reviewId) {
        return feedbackRepository.findByReviewId(reviewId)
                .stream()
                .map(feedbackMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackReadDTO> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(feedbackMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackReadDTO updateFeedback(Long id, FeedbackCreateUpdateDTO dto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id " + id));
        feedbackMapper.partialUpdate(dto, feedback);
        Feedback updated = feedbackRepository.save(feedback);
        return feedbackMapper.toReadDto(updated);
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id " + id));
        feedbackRepository.delete(feedback);
    }
}

