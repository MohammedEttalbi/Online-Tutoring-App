package com.example.evaluationservice.domain.dto.Rating;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import java.io.Serializable;

@Value
@Schema(description = "DTO de lecture d'un rating")
public class RatingReadDTO implements Serializable {

    @Schema(description = "ID du rating", example = "3")
    Long id;

    @Schema(description = "Nombre d'étoiles attribuées", example = "5")
    Integer stars;
}

