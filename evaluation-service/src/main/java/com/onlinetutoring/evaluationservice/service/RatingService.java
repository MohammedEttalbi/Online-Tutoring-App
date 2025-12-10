package com.onlinetutoring.evaluationservice.service;


import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDTO;

import java.util.List;

public interface RatingService {

    RatingReadDTO createRating(RatingCreateUpdateDTO dto);

    RatingReadDTO getRatingById(Long id);

    RatingReadDTO getRatingByReviewId(Long reviewId);

    List<RatingReadDTO> getAllRatings();

    RatingReadDTO updateRating(Long id, RatingCreateUpdateDTO dto);

    void deleteRating(Long id);
}

