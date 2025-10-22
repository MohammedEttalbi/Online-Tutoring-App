package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.domain.entity.Subject;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    Subject toEntity(SubjectCreateUpdateDto subjectCreateUpdateDto);

    SubjectReadDto toReadDto(Subject subject);

    Set<SubjectReadDto> toReadDtoSet(Set<Subject> subjects);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject partialUpdate(SubjectCreateUpdateDto subjectCreateUpdateDto, @MappingTarget Subject subject);
}