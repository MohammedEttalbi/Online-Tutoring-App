package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.material.MaterialCreateDto;
import org.example.service_sessions.domain.dto.material.MaterialReadDto;
import org.example.service_sessions.domain.dto.material.MaterialUpdateDto;
import org.example.service_sessions.domain.entity.Material;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {

    Material toEntity(MaterialCreateDto dto);

    MaterialReadDto toReadDto(Material entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(MaterialUpdateDto dto, @MappingTarget Material entity);
}

