package com.onlinetutoring.service_sessions.service.impl;

import com.onlinetutoring.service_sessions.common.exception.NotFoundException;
import com.onlinetutoring.service_sessions.domain.dto.material.*;
import com.onlinetutoring.service_sessions.domain.entity.Material;
import com.onlinetutoring.service_sessions.mapper.MaterialMapper;
import com.onlinetutoring.service_sessions.repository.MaterialRepository;
import com.onlinetutoring.service_sessions.service.IServiceMaterial;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ServiceMaterialImpl implements IServiceMaterial {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;


    public ServiceMaterialImpl(MaterialRepository materialRepository, MaterialMapper materialMapper) {

        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }


    @Override
    public MaterialReadDto create(MaterialCreateDto dto) {

        if (materialRepository.existsByName(dto.getName())) {

            throw new DataIntegrityViolationException("Material name already exists: " + dto.getName());
        }

        Material entity = materialMapper.toEntity(dto);
        Material saved = materialRepository.save(entity);

        return materialMapper.toReadDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public MaterialReadDto getById(Long id) {

        Material m = materialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Material not found: id=" + id));

        return materialMapper.toReadDto(m);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MaterialReadDto> getAll(Pageable pageable) {

        return materialRepository.findAll(pageable).map(materialMapper::toReadDto);
    }


    @Override
    public MaterialReadDto update(MaterialUpdateDto dto) {

        Material entity = materialRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Material not found: id=" + dto.getId()));

        if (dto.getName() != null && !dto.getName().equals(entity.getName())) {

            if (materialRepository.existsByName(dto.getName())) {

                throw new DataIntegrityViolationException("Material name already exists: " + dto.getName());
            }
        }

        materialMapper.update(dto, entity);
        Material saved = materialRepository.save(entity);

        return materialMapper.toReadDto(saved);
    }


    @Override
    public void delete(MaterialDeleteDto dto) {

        Material entity = materialRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Material not found: id=" + dto.getId()));

        materialRepository.delete(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return materialRepository.existsByName(name);
    }


    @Override
    @Transactional(readOnly = true)
    public MaterialReadDto findByName(String name) {

        return materialRepository.findByName(name)
                .map(materialMapper::toReadDto)
                .orElseThrow(() -> new NotFoundException("Material not found by name: " + name));
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MaterialReadDto> searchByName(String namePart, Pageable pageable) {

        return materialRepository.findAllByNameContainingIgnoreCase(namePart, pageable)
                .map(materialMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MaterialReadDto> listByType(String type, Pageable pageable) {

        return materialRepository.findAllByTypeIgnoreCase(type, pageable)
                .map(materialMapper::toReadDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MaterialReadDto> listBySession(Long sessionId, Pageable pageable) {

        return materialRepository.findAllBySessions_Id(sessionId, pageable)
                .map(materialMapper::toReadDto);
    }
}

