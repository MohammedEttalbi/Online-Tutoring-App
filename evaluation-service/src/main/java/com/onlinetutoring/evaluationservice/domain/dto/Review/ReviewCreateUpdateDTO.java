package com.onlinetutoring.evaluationservice.domain.dto.Review;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO pour créer ou mettre à jour une review")
public class ReviewCreateUpdateDTO implements Serializable {

    @NotNull
    @Schema(description = "ID de l'étudiant", example = "1", required = true)
    private Long studentId;

    @NotNull
    @Schema(description = "ID du tuteur", example = "2", required = true)
    private Long tutorId;

    @NotBlank
    @Schema(description = "Commentaire de la review", example = "Très bon cours, tuteur très clair", required = true)
    private String comment;
}
