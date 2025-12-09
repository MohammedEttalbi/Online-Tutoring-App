package com.example.evaluationservice.domain.dto.Feedback;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "DTO utilisé pour créer ou mettre à jour un feedback")
public class FeedbackCreateUpdateDTO implements Serializable {

    @NotBlank
    @Schema(description = "Message du feedback", example = "Très utile comme conseil")
    private String message;

    @NotNull
    @Schema(description = "ID de la review liée", example = "10")
    private Long reviewId;
}



