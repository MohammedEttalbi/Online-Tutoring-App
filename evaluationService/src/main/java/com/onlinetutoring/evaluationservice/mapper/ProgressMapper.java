package com.onlinetutoring.evaluationservice.mapper;


import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Progress.ProgressReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Progress;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ProgressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "review", ignore = true)
    Progress toEntity(ProgressCreateUpdateDto dto);

    ProgressReadDto toReadDto(Progress progress);

    List<ProgressReadDto> toReadDtoList(List<Progress> progresses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Progress partialUpdate(ProgressCreateUpdateDto dto, @MappingTarget Progress progress);
}

