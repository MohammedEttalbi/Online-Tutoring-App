package com.onlinetutoring.evaluationservice.domain.dto.Progress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@Schema(description = "DTO de lecture d’un enregistrement de progression")
public class ProgressReadDTO implements Serializable {

    @Schema(description = "ID du record", example = "1")
    Long id;

    @Schema(description = "ID de l'étudiant", example = "12")
    Long studentId;

    @Schema(description = "ID du tuteur", example = "5")
    Long tutorId;

    @Schema(description = "Score de progression", example = "85")
    Integer score;

    @Schema(description = "Niveau actuel", example = "advanced")
    String level;

    @Schema(description = "Date de mise à jour", example = "2025-11-25T14:30:00")
    LocalDateTime updatedAt;
}



