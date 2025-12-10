package com.onlinetutoring.evaluationservice.controller;



import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDTO;
import com.onlinetutoring.evaluationservice.service.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@Tag(name = "Progress API", description = "Gestion de l’évolution et des scores des étudiants")
public class ProgressController {

    private final ProgressService progressService;

    @Operation(summary = "Create progress", description = "Créer un nouvel enregistrement d’évolution")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Progress created successfully",
                    content = @Content(schema = @Schema(implementation = ProgressReadDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ProgressReadDTO> createProgress(
            @Valid @RequestBody ProgressCreateUpdateDTO dto) {
        return ResponseEntity.ok(progressService.createProgress(dto));
    }

    @Operation(summary = "Get progress by ID", description = "Récupérer un enregistrement de progression par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Progress found",
                    content = @Content(schema = @Schema(implementation = ProgressReadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Progress not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProgressReadDTO> getProgressById(@PathVariable Long id) {
        return ResponseEntity.ok(progressService.getProgressById(id));
    }

    @Operation(summary = "Get progress by student ID",
            description = "Récupérer toute l’évolution d’un étudiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of progress retrieved"),
    })
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ProgressReadDTO>> getProgressByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(progressService.getProgressByStudentId(studentId));
    }

    @Operation(
            summary = "Get progress by student and tutor",
            description = "Récupérer la progression d’un étudiant suivie par un tuteur spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of progress retrieved"),
    })
    @GetMapping("/student/{studentId}/tutor/{tutorId}")
    public ResponseEntity<List<ProgressReadDTO>> getProgressByStudentAndTutor(
            @PathVariable Long studentId,
            @PathVariable Long tutorId) {
        return ResponseEntity.ok(progressService.getProgressByStudentAndTutor(studentId, tutorId));
    }

    @Operation(summary = "Get all progress records", description = "Récupérer tous les enregistrements de progression")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of progress retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<ProgressReadDTO>> getAllProgress() {
        return ResponseEntity.ok(progressService.getAllProgress());
    }

    @Operation(summary = "Update progress", description = "Modifier un enregistrement de progression")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Progress updated successfully"),
            @ApiResponse(responseCode = "404", description = "Progress not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProgressReadDTO> updateProgress(
            @PathVariable Long id,
            @Valid @RequestBody ProgressCreateUpdateDTO dto) {
        return ResponseEntity.ok(progressService.updateProgress(id, dto));
    }

    @Operation(summary = "Delete progress", description = "Supprimer un enregistrement de progression")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Progress deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Progress not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}
