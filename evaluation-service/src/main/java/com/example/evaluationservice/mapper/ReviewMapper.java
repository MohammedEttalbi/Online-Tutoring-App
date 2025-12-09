package com.example.evaluationservice.mapper;



import com.example.evaluationservice.domain.dto.Review.ReviewCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Review.ReviewReadDTO;
import com.example.evaluationservice.domain.entity.Review;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,uses = {FeedbackMapper.class})
public interface ReviewMapper {

    Review toEntity(ReviewCreateUpdateDTO dto);

    ReviewReadDTO toReadDto(Review review);

    Set<ReviewReadDTO> toReadDtoSet(Set<Review> reviews);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ReviewCreateUpdateDTO dto, @MappingTarget Review review);
}
