package com.onlinetutoring.evaluationservice.controller;


import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;
import com.onlinetutoring.evaluationservice.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback API", description = "Gestion des feedbacks associés aux reviews")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(summary = "Create feedback",
            description = "Créer un nouveau feedback lié à une review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback created successfully",
                    content = @Content(schema = @Schema(implementation = FeedbackReadDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<FeedbackReadDTO> createFeedback(
            @Valid @RequestBody FeedbackCreateUpdateDTO dto) {
        return ResponseEntity.ok(feedbackService.createFeedback(dto));
    }

    @Operation(summary = "Get feedback by ID",
            description = "Récupérer un feedback par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback retrieved",
                    content = @Content(schema = @Schema(implementation = FeedbackReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Feedback not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackReadDTO> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @Operation(summary = "Get feedbacks by review ID",
            description = "Récupérer la liste des feedbacks liés à une review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of feedback retrieved")
    })
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<FeedbackReadDTO>> getFeedbacksByReviewId(@PathVariable Long reviewId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByReviewId(reviewId));
    }

    @Operation(summary = "Get all feedbacks",
            description = "Récupérer tous les feedbacks enregistrés")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of feedback retrieved")
    })
    @GetMapping
    public ResponseEntity<List<FeedbackReadDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @Operation(summary = "Update feedback",
            description = "Modifier un feedback existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback updated successfully"),
            @ApiResponse(responseCode = "404", description = "Feedback not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackReadDTO> updateFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackCreateUpdateDTO dto) {
        return ResponseEntity.ok(feedbackService.updateFeedback(id, dto));
    }

    @Operation(summary = "Delete feedback",
            description = "Supprimer un feedback par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Feedback deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Feedback not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}


