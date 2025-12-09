package com.example.evaluationservice.controller;


import com.example.evaluationservice.domain.dto.Rating.RatingCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Rating.RatingReadDTO;
import com.example.evaluationservice.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Rating", description = "Endpoints pour la gestion des ratings (notes)")
@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @Operation(summary = "Créer un nouveau rating")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rating créé avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingReadDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<RatingReadDTO> createRating(
            @Valid @RequestBody RatingCreateUpdateDTO dto) {
        return ResponseEntity.ok(ratingService.createRating(dto));
    }



    @Operation(summary = "Récupérer un rating par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rating trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rating non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RatingReadDTO> getRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }



    @Operation(summary = "Récupérer le rating associé à une review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rating trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rating non trouvé")
    })
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<RatingReadDTO> getRatingByReviewId(@PathVariable Long reviewId) {
        return ResponseEntity.ok(ratingService.getRatingByReviewId(reviewId));
    }


    @Operation(summary = "Récupérer tous les ratings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des ratings",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingReadDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<RatingReadDTO>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }




    @Operation(summary = "Mettre à jour un rating")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rating mis à jour avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rating non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RatingReadDTO> updateRating(
            @PathVariable Long id,
            @Valid @RequestBody RatingCreateUpdateDTO dto) {
        return ResponseEntity.ok(ratingService.updateRating(id, dto));
    }




    @Operation(summary = "Supprimer un rating")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rating supprimé"),
            @ApiResponse(responseCode = "404", description = "Rating non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}


