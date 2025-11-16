package com.onlinetutoring.evaluationservice.domain.dto.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackCreateUpdateDto {
    String comment;
    Long reviewId;
}

