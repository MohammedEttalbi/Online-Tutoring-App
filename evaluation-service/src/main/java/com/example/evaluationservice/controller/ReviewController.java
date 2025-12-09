package com.example.evaluationservice.controller;


import com.example.evaluationservice.domain.dto.Review.ReviewCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Review.ReviewReadDTO;
import com.example.evaluationservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review", description = "Endpoints pour gérer les reviews")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // --------------------------
    // CREATE
    // --------------------------
    @Operation(summary = "Créer une nouvelle review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review créée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<ReviewReadDTO> createReview(
            @Valid @RequestBody ReviewCreateUpdateDTO dto) {
        return ResponseEntity.ok(reviewService.createReview(dto));
    }

    @Operation(summary = "Récupérer une review par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Review non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewReadDTO> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @Operation(summary = "Récupérer toutes les reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des reviews",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<ReviewReadDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @Operation(summary = "Récupérer toutes les reviews d'un tuteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des reviews du tuteur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class)))
    })
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<ReviewReadDTO>> getReviewsByTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(reviewService.getReviewsByTutorId(tutorId));
    }

    @Operation(summary = "Récupérer toutes les reviews d'un étudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des reviews de l'étudiant",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class)))
    })
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ReviewReadDTO>> getReviewsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(reviewService.getReviewsByStudentId(studentId));
    }


    @Operation(summary = "Mettre à jour une review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review mise à jour",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Review non trouvée")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewReadDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewCreateUpdateDTO dto) {
        return ResponseEntity.ok(reviewService.updateReview(id, dto));
    }


    @Operation(summary = "Supprimer une review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Review supprimée"),
            @ApiResponse(responseCode = "404", description = "Review non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}

