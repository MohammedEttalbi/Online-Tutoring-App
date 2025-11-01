package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.BookingDTO;
import org.example.service_sessions.domain.entity.Booking;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    Booking toEntity(BookingDTO qualificationDto);

    BookingDTO toDto(Booking qualification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingDTO qualificationDto, @MappingTarget Booking qualification);
}

