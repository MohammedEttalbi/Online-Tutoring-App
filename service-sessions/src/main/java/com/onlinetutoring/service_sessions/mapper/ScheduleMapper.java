package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleReadDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleUpdateDto;
import com.onlinetutoring.service_sessions.domain.entity.Schedule;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    Schedule toEntity(ScheduleCreateDto dto);

    ScheduleReadDto toReadDto(Schedule entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ScheduleUpdateDto dto, @MappingTarget Schedule entity);
}

