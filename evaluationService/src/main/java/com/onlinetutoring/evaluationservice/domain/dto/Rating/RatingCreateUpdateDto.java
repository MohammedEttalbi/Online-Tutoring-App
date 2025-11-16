package com.onlinetutoring.evaluationservice.domain.dto.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingCreateUpdateDto {
    int score;
    String criteria;
    Long reviewId;
}

