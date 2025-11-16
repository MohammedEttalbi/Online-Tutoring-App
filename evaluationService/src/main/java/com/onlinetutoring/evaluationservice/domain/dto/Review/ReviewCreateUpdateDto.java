package com.onlinetutoring.evaluationservice.domain.dto.Review;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewCreateUpdateDto {

    @NotBlank
    private String reviewerName;

    @NotBlank
    private String subject;

    private String description;
}
