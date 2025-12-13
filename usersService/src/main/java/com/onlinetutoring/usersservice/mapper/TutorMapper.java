package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.tutor.TutorReadDto;
import com.onlinetutoring.usersservice.domain.entity.Tutor;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        SubjectMapper.class, QualificationMapper.class })
public interface TutorMapper {
    Tutor toEntity(TutorCreateUpdateDto tutorCreateUpdateDto);

    TutorReadDto toReadDto(Tutor tutor);

    Set<TutorReadDto> toReadDtoSet(Set<Tutor> tutors);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tutor partialUpdate(TutorCreateUpdateDto tutorCreateUpdateDto, @MappingTarget Tutor tutor);
}