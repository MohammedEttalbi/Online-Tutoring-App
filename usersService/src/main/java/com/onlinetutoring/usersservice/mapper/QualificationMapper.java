package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.domain.dto.student.StudentReadDto;
import com.onlinetutoring.usersservice.domain.entity.Qualification;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QualificationMapper {
    Qualification toEntity(QualificationCreateUpdateDto qualificationCreateUpdateDto);

    QualificationReadDto toReadDto(Qualification qualification);

    Set<QualificationReadDto> toReadDtoSet(Set<Qualification> qualifications);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Qualification partialUpdate(QualificationCreateUpdateDto qualificationCreateUpdateDto, @MappingTarget Qualification qualification);
}