package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.schedule.ScheduleCreateDto;
import org.example.service_sessions.domain.dto.schedule.ScheduleReadDto;
import org.example.service_sessions.domain.dto.schedule.ScheduleUpdateDto;
import org.example.service_sessions.domain.entity.Schedule;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    Schedule toEntity(ScheduleCreateDto dto);

    ScheduleReadDto toReadDto(Schedule entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ScheduleUpdateDto dto, @MappingTarget Schedule entity);
}

