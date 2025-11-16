package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.dto.booking.BookingUpdateDto;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import com.onlinetutoring.service_sessions.domain.entity.Session;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    default Booking toEntity(BookingCreateDto dto) {
        if (dto == null) return null;
        Booking b = new Booking();
        b.setDateTime(dto.getDateTime());
        if (dto.getSessionId() != null) {
            Session s = new Session();
            s.setId(dto.getSessionId());
            b.setSession(s);
        }
        return b;
    }

    default BookingReadDto toReadDto(Booking entity) {
        if (entity == null) return null;
        BookingReadDto dto = new BookingReadDto();
        dto.setId(entity.getId());
        dto.setDateTime(entity.getDateTime());
        dto.setSessionId(entity.getSession() != null ? entity.getSession().getId() : null);
        return dto;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default void update(BookingUpdateDto dto, @MappingTarget Booking entity) {
        if (dto == null || entity == null) return;
        if (dto.getDateTime() != null) entity.setDateTime(dto.getDateTime());
        if (dto.getSessionId() != null) {
            Session s = new Session();
            s.setId(dto.getSessionId());
            entity.setSession(s);
        }
    }
}

