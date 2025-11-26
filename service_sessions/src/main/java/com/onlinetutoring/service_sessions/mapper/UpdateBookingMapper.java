package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingUpdateDto;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UpdateBookingMapper {
    Booking toEntity(BookingUpdateDto bookingUpdateDto);

    BookingUpdateDto toDto(Booking booking);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingUpdateDto bookingUpdateDto, @MappingTarget Booking booking);
}