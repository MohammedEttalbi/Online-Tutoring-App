package com.onlinetutoring.service_sessions.controller;

import com.onlinetutoring.service_sessions.domain.dto.material.MaterialCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialDeleteDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialReadDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialUpdateDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionReadDto;
import com.onlinetutoring.service_sessions.service.impl.ServiceMaterialImpl;
import com.onlinetutoring.service_sessions.service.impl.ServiceSessionImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final ServiceMaterialImpl serviceMaterialImpl;
    private final ServiceSessionImpl serviceSessionImpl;

    public MaterialController(ServiceMaterialImpl serviceMaterialImpl,
            ServiceSessionImpl serviceSessionImpl) {
        this.serviceMaterialImpl = serviceMaterialImpl;
        this.serviceSessionImpl = serviceSessionImpl;
    }

    /**
     * Get all materials (paginated)
     * GET /materials
     */
    @GetMapping
    public ResponseEntity<Page<MaterialReadDto>> getAllMaterials(
            @PageableDefault(size = 20) Pageable pageable) {
        Page<MaterialReadDto> materials = serviceMaterialImpl.getAll(pageable);
        return ResponseEntity.ok(materials);
    }

    /**
     * Get material by ID
     * GET /materials/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<MaterialReadDto> getMaterialById(@PathVariable Long id) {
        MaterialReadDto material = serviceMaterialImpl.getById(id);
        return ResponseEntity.ok(material);
    }

    /**
     * Get material by name
     * GET /materials/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<MaterialReadDto> getMaterialByName(@PathVariable String name) {
        MaterialReadDto material = serviceMaterialImpl.findByName(name);
        return ResponseEntity.ok(material);
    }

    /**
     * Search materials by name
     * GET /materials/search?name=...
     */
    @GetMapping("/search")
    public ResponseEntity<Page<MaterialReadDto>> searchMaterialsByName(
            @RequestParam String name,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<MaterialReadDto> materials = serviceMaterialImpl.searchByName(name, pageable);
        return ResponseEntity.ok(materials);
    }

    /**
     * Get materials by type
     * GET /materials/type/{type}
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<Page<MaterialReadDto>> getMaterialsByType(
            @PathVariable String type,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<MaterialReadDto> materials = serviceMaterialImpl.listByType(type, pageable);
        return ResponseEntity.ok(materials);
    }

    /**
     * Get materials by session ID
     * GET /materials/session/{sessionId}
     */
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<Page<MaterialReadDto>> getMaterialsBySession(
            @PathVariable Long sessionId,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<MaterialReadDto> materials = serviceMaterialImpl.listBySession(sessionId, pageable);
        return ResponseEntity.ok(materials);
    }

    /**
     * Check if material exists by name
     * GET /materials/exists?name=...
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkMaterialExists(@RequestParam String name) {
        boolean exists = serviceMaterialImpl.existsByName(name);
        return ResponseEntity.ok(exists);
    }

    /**
     * Create a new material
     * POST /materials
     */
    @PostMapping
    public ResponseEntity<MaterialReadDto> createMaterial(@Valid @RequestBody MaterialCreateDto materialCreateDto) {
        MaterialReadDto created = serviceMaterialImpl.create(materialCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update an existing material
     * PUT /materials
     */
    @PutMapping
    public ResponseEntity<MaterialReadDto> updateMaterial(@Valid @RequestBody MaterialUpdateDto materialUpdateDto) {
        MaterialReadDto updated = serviceMaterialImpl.update(materialUpdateDto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a material
     * DELETE /materials
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteMaterial(@Valid @RequestBody MaterialDeleteDto materialDeleteDto) {
        serviceMaterialImpl.delete(materialDeleteDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Alternative: Delete a material by ID (RESTful style)
     * DELETE /materials/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialById(@PathVariable Long id) {
        MaterialDeleteDto deleteDto = new MaterialDeleteDto();
        deleteDto.setId(id);
        serviceMaterialImpl.delete(deleteDto);
        return ResponseEntity.noContent().build();
    }

    // ========== ManyToMany Relationship Endpoints ==========

    /**
     * Get all sessions that use this material
     * GET /materials/{id}/sessions
     */
    @GetMapping("/{id}/sessions")
    public ResponseEntity<Page<SessionReadDto>> getSessionsByMaterial(
            @PathVariable Long id,
            @PageableDefault(size = 20) Pageable pageable) {
        // First verify material exists
        serviceMaterialImpl.getById(id);
        Page<SessionReadDto> sessions = serviceSessionImpl.listByMaterial(id, pageable);
        return ResponseEntity.ok(sessions);
    }
}
