package com.onlinetutoring.evaluationservice.mapper;


import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Feedback;
import org.mapstruct.*;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FeedbackMapper {

    Feedback toEntity(FeedbackCreateUpdateDTO dto);

    FeedbackReadDTO toReadDto(Feedback feedback);

    Set<FeedbackReadDTO> toReadDtoSet(Set<Feedback> feedbacks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Feedback partialUpdate(FeedbackCreateUpdateDTO dto, @MappingTarget Feedback feedback);
}

