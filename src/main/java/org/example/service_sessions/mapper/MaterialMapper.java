package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.MaterialDTO;
import org.example.service_sessions.domain.entity.Material;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {

    Material toEntity(MaterialDTO qualificationDto);

    MaterialDTO toDto(Material qualification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Material partialUpdate(MaterialDTO qualificationDto, @MappingTarget Material qualification);
}

