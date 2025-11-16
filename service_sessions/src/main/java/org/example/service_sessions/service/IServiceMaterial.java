package org.example.service_sessions.service;

import org.example.service_sessions.domain.dto.material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IServiceMaterial {

    MaterialReadDto create(MaterialCreateDto dto);

    MaterialReadDto getById(Long id);

    Page<MaterialReadDto> getAll(Pageable pageable);

    MaterialReadDto update(MaterialUpdateDto dto);

    void delete(MaterialDeleteDto dto);

    boolean existsByName(String name);

    MaterialReadDto findByName(String name);

    Page<MaterialReadDto> searchByName(String namePart, Pageable pageable);

    Page<MaterialReadDto> listByType(String type, Pageable pageable);

    Page<MaterialReadDto> listBySession(Long sessionId, Pageable pageable);
}

