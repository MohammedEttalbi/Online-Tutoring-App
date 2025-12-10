package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingCreateDto;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateBookingMapper {
    Booking toEntity(BookingCreateDto bookingCreateDto);

    BookingCreateDto toDto(Booking booking);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingCreateDto bookingCreateDto, @MappingTarget Booking booking);
}