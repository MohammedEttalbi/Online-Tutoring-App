package com.onlinetutoring.evaluationservice.domain.dto.Review;



import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Schema(description = "DTO de lecture d'une review")
public class ReviewReadDTO implements Serializable {

    @Schema(description = "ID de la review", example = "1")
    Long id;

    @Schema(description = "ID de l'étudiant qui a laissé la review", example = "12")
    Long studentId;

    @Schema(description = "ID du tuteur évalué", example = "5")
    Long tutorId;

    @Schema(description = "Commentaire laissé par l'étudiant", example = "Un excellent cours, très clair.")
    String comment;

    @Schema(description = "Date de création de la review", example = "2025-11-23T18:00:00")
    LocalDateTime createdAt;

    @Schema(description = "Note associée à cette review")
    RatingReadDTO rating;

    @Schema(description = "Liste des feedbacks associés à cette review")
    List<FeedbackReadDTO> feedbackList;
}

