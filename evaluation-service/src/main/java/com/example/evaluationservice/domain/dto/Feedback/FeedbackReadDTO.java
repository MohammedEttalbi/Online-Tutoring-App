package com.example.evaluationservice.domain.dto.Feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@Schema(description = "DTO de lecture d’un feedback")
public class FeedbackReadDTO implements Serializable {

    @Schema(description = "ID du feedback", example = "1")
    Long id;

    @Schema(description = "Message du feedback", example = "Très bon suivi, merci !")
    String message;

    @Schema(description = "Date de création", example = "2025-11-25T14:30:00")
    LocalDateTime createdAt;
}



