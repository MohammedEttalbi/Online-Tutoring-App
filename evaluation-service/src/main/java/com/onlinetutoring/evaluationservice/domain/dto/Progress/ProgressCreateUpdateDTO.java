package com.onlinetutoring.evaluationservice.domain.dto.Progress;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "DTO pour créer ou mettre à jour la progression d’un étudiant")
public class ProgressCreateUpdateDTO implements Serializable {

    @NotNull
    @Schema(description = "ID de l'étudiant", example = "12")
    private Long studentId;

    @NotNull
    @Schema(description = "ID du tuteur", example = "5")
    private Long tutorId;

    @NotNull
    @Min(0) @Max(100)
    @Schema(description = "Score de progression", example = "85")
    private Integer score;

    @NotBlank
    @Schema(description = "Niveau de l’étudiant", example = "intermediate")
    private String level;
}



