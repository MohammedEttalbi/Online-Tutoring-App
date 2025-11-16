package com.onlinetutoring.evaluationservice.service.Impl;

import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import com.onlinetutoring.evaluationservice.repository.RatingRepository;
import com.onlinetutoring.evaluationservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, Rating rating) {
        Rating existing = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        existing.setScore(rating.getScore());
        existing.setCriteria(rating.getCriteria());
        return ratingRepository.save(existing);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
