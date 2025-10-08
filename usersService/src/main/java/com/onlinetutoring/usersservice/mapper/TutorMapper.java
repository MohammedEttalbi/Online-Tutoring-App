package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.TutorDto;
import com.onlinetutoring.usersservice.domain.entity.Tutor;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TutorMapper {
    Tutor toEntity(TutorDto tutorDto);

    TutorDto toDto(Tutor tutor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tutor partialUpdate(TutorDto tutorDto, @MappingTarget Tutor tutor);
}