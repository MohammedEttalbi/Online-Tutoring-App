package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.material.MaterialCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialReadDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialUpdateDto;
import com.onlinetutoring.service_sessions.domain.entity.Material;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {

    Material toEntity(MaterialCreateDto dto);

    MaterialReadDto toReadDto(Material entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(MaterialUpdateDto dto, @MappingTarget Material entity);
}

