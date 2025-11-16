package com.onlinetutoring.evaluationservice.mapper;



import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Feedback;
import org.mapstruct.*;


import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface FeedbackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "review", ignore = true)
    Feedback toEntity(FeedbackCreateUpdateDto dto);

    FeedbackReadDto toReadDto(Feedback feedback);

    List<FeedbackReadDto> toReadDtoList(List<Feedback> feedbacks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Feedback partialUpdate(FeedbackCreateUpdateDto dto, @MappingTarget Feedback feedback);
}

