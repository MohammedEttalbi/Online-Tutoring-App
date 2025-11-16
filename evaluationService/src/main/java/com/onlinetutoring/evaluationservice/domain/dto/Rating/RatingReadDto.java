package com.onlinetutoring.evaluationservice.domain.dto.Rating;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.evaluationservice.domain.entity.Rating}
 */
@Value
public class RatingReadDto implements Serializable {
    Long id;
    int score;
    String criteria;
}