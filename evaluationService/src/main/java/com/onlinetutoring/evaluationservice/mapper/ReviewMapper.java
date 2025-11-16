package com.onlinetutoring.evaluationservice.mapper;


import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Review.ReviewReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import org.mapstruct.*;


import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RatingMapper.class, FeedbackMapper.class, ProgressMapper.class}
)
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "progresses", ignore = true)
    Review toEntity(ReviewCreateUpdateDto dto);

    ReviewReadDto toReadDto(Review review);

    List<ReviewReadDto> toReadDtoList(List<Review> reviews);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ReviewCreateUpdateDto dto, @MappingTarget Review review);
}
