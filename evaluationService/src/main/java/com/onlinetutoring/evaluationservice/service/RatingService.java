package com.onlinetutoring.evaluationservice.service;

import com.onlinetutoring.evaluationservice.domain.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    Rating updateRating(Long id, Rating rating);
    void deleteRating(Long id);
    Rating getRatingById(Long id);
    List<Rating> getAllRatings();
}

