package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.ScheduleDTO;
import org.example.service_sessions.domain.entity.Schedule;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    Schedule toEntity(ScheduleDTO qualificationDto);

    ScheduleDTO toDto(Schedule qualification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule partialUpdate(ScheduleDTO qualificationDto, @MappingTarget Schedule qualification);
}

