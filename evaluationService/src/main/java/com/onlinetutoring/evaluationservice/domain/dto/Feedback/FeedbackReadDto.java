package com.onlinetutoring.evaluationservice.domain.dto.Feedback;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.onlinetutoring.evaluationservice.domain.entity.Feedback}
 */
@Value
public class FeedbackReadDto implements Serializable {
    Long id;
    String comment;
    LocalDate date;
}