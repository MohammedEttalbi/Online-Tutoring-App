package com.onlinetutoring.evaluationservice.mapper;



import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import org.mapstruct.*;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RatingMapper {

    Rating toEntity(RatingCreateUpdateDTO dto);

    RatingReadDTO toReadDto(Rating rating);

    Set<RatingReadDTO> toReadDtoSet(Set<Rating> ratings);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rating partialUpdate(RatingCreateUpdateDTO dto, @MappingTarget Rating rating);
}

