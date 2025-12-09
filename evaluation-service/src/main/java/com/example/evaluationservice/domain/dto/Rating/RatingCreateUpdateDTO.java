package com.example.evaluationservice.domain.dto.Rating;



import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Schema(description = "DTO pour créer ou mettre à jour une note (rating)")
public class RatingCreateUpdateDTO implements Serializable {

    @Schema(description = "Nombre d'étoiles attribuées (1 à 5)", example = "4")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer stars;

    @Schema(description = "ID de la review associée", example = "12")
    @NotNull
    private Long reviewId;
}



