package com.example.evaluationservice.mapper;


import com.example.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Progress.ProgressReadDTO;
import com.example.evaluationservice.domain.entity.Progress;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgressMapper {

    Progress toEntity(ProgressCreateUpdateDTO dto);

    ProgressReadDTO toReadDto(Progress progress);

    Set<ProgressReadDTO> toReadDtoSet(Set<Progress> progresses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Progress partialUpdate(ProgressCreateUpdateDTO dto, @MappingTarget Progress progress);
}

