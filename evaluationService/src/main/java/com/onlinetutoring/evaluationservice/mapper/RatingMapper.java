package com.onlinetutoring.evaluationservice.mapper;


import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface RatingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "review", ignore = true)
    Rating toEntity(RatingCreateUpdateDto dto);

    RatingReadDto toReadDto(Rating rating);

    List<RatingReadDto> toReadDtoList(List<Rating> ratings);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rating partialUpdate(RatingCreateUpdateDto dto, @MappingTarget Rating rating);
}
