package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReadBookingMapper {
    Booking toEntity(BookingReadDto bookingReadDto);

    BookingReadDto toDto(Booking booking);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingReadDto bookingReadDto, @MappingTarget Booking booking);
}