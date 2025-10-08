package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.QualificationDto;
import com.onlinetutoring.usersservice.domain.entity.Qualification;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QualificationMapper {
    Qualification toEntity(QualificationDto qualificationDto);

    QualificationDto toDto(Qualification qualification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Qualification partialUpdate(QualificationDto qualificationDto, @MappingTarget Qualification qualification);
}