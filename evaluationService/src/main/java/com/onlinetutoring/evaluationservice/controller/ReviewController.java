package com.onlinetutoring.evaluationservice.controller;

import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import com.onlinetutoring.evaluationservice.mapper.ReviewMapper;
import com.onlinetutoring.evaluationservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public List<ReviewReadDto> getAll() {
        return reviewService.getAllReviews()
                .stream()
                .map(reviewMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ReviewReadDto getOne(@PathVariable Long id) {
        return reviewMapper.toReadDto(reviewService.getReviewById(id));
    }

    @PostMapping
    public ReviewReadDto create(@RequestBody ReviewCreateUpdateDto dto) {
        Review review = reviewMapper.toEntity(dto);
        Review saved = reviewService.createReview(review);
        return reviewMapper.toReadDto(saved);
    }

    @PutMapping("/{id}")
    public ReviewReadDto update(@PathVariable Long id, @RequestBody ReviewCreateUpdateDto dto) {
        Review review = reviewMapper.toEntity(dto);
        Review updated = reviewService.updateReview(id, review);
        return reviewMapper.toReadDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
