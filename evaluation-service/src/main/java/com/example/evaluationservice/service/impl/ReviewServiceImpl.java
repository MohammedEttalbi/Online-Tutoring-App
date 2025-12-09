package com.example.evaluationservice.service.impl;


import com.example.evaluationservice.domain.dto.Review.ReviewCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Review.ReviewReadDTO;
import com.example.evaluationservice.domain.entity.Review;
import com.example.evaluationservice.exception.ResourceNotFoundException;
import com.example.evaluationservice.mapper.ReviewMapper;
import com.example.evaluationservice.repository.ReviewRepository;
import com.example.evaluationservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewReadDTO createReview(ReviewCreateUpdateDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        review.setCreatedAt(LocalDateTime.now());
        Review saved = reviewRepository.save(review);
        return reviewMapper.toReadDto(saved);
    }

    @Override
    public ReviewReadDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
        return reviewMapper.toReadDto(review);
    }

    @Override
    public List<ReviewReadDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewReadDTO> getReviewsByTutorId(Long tutorId) {
        return reviewRepository.findByTutorId(tutorId)
                .stream()
                .map(reviewMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewReadDTO> getReviewsByStudentId(Long studentId) {
        return reviewRepository.findByStudentId(studentId)
                .stream()
                .map(reviewMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewReadDTO updateReview(Long id, ReviewCreateUpdateDTO dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
        reviewMapper.partialUpdate(dto, review);
        Review updated = reviewRepository.save(review);
        return reviewMapper.toReadDto(updated);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
        reviewRepository.delete(review);
    }
}

