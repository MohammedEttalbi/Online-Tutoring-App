package com.example.evaluationservice.mapper;


import com.example.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDTO;
import com.example.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;
import com.example.evaluationservice.domain.entity.Feedback;
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

