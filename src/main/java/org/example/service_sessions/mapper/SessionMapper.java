package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.SessionDTO;
import org.example.service_sessions.domain.entity.Session;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {

    Session toEntity(SessionDTO qualificationDto);

    SessionDTO toDto(Session qualification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Session partialUpdate(SessionDTO qualificationDto, @MappingTarget Session qualification);
}

