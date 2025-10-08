package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.SubjectDto;
import com.onlinetutoring.usersservice.domain.entity.Subject;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    Subject toEntity(SubjectDto subjectDto);

    SubjectDto toDto(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject partialUpdate(SubjectDto subjectDto, @MappingTarget Subject subject);
}