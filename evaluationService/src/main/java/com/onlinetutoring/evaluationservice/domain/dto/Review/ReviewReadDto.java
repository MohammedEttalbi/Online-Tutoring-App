package com.onlinetutoring.evaluationservice.domain.dto.Review;

import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDto;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDto;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.onlinetutoring.evaluationservice.domain.entity.Review}
 */
@Value
public class ReviewReadDto implements Serializable {
    Long id;
    String reviewerName;
    String subject;
    String description;
    LocalDate reviewDate;
    RatingReadDto rating;
    List<FeedbackReadDto> feedbacks;
    List<ProgressReadDto> progresses;

}