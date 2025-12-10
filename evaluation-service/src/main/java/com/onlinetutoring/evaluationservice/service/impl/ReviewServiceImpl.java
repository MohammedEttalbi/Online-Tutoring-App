package com.onlinetutoring.evaluationservice.service.impl;

import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import com.onlinetutoring.evaluationservice.exception.ResourceNotFoundException;
import com.onlinetutoring.evaluationservice.feignrequests.ResilientFeignRequests;
import com.onlinetutoring.evaluationservice.feignrequests.StudentDto;
import com.onlinetutoring.evaluationservice.feignrequests.TutorDto;
import com.onlinetutoring.evaluationservice.mapper.ReviewMapper;
import com.onlinetutoring.evaluationservice.repository.ReviewRepository;
import com.onlinetutoring.evaluationservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ResilientFeignRequests resilientFeignRequests;

    @Override
    public ReviewReadDTO createReview(ReviewCreateUpdateDTO dto) {
        // Validate student exists
        validateStudentExists(dto.getStudentId());
        // Validate tutor exists
        validateTutorExists(dto.getTutorId());

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

        // Validate student exists if being updated
        if (dto.getStudentId() != null) {
            validateStudentExists(dto.getStudentId());
        }
        // Validate tutor exists if being updated
        if (dto.getTutorId() != null) {
            validateTutorExists(dto.getTutorId());
        }

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
