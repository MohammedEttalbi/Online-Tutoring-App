package com.onlinetutoring.service_sessions.mapper;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialReadDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleReadDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionCreateDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionReadDto;
import com.onlinetutoring.service_sessions.domain.dto.session.SessionUpdateDto;
import com.onlinetutoring.service_sessions.domain.entity.Booking;
import com.onlinetutoring.service_sessions.domain.entity.Material;
import com.onlinetutoring.service_sessions.domain.entity.Schedule;
import com.onlinetutoring.service_sessions.domain.entity.Session;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        MaterialMapper.class, ScheduleMapper.class, BookingMapper.class })
public interface SessionMapper {

    default Session toEntity(SessionCreateDto dto) {
        if (dto == null)
            return null;
        Session s = new Session();
        s.setName(dto.name);
        s.setDuration(dto.duration);
        s.setTutorId(dto.tutorId);
        if (dto.scheduleId != null) {
            Schedule sch = new Schedule();
            sch.setId(dto.scheduleId);
            s.setSchedule(sch);
        }
        if (dto.materialIds != null) {
            s.setMaterials(mapMaterialIds(dto.materialIds));
        }
        return s;
    }

    default SessionReadDto toReadDto(Session entity) {
        if (entity == null)
            return null;
        SessionReadDto dto = new SessionReadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setTutorId(entity.getTutorId());

        // Map nested schedule
        dto.setSchedule(toScheduleReadDto(entity.getSchedule()));

        // Map nested materials
        dto.setMaterials(toMaterialReadDtos(entity.getMaterials()));

        // Map nested bookings
        dto.setBookings(toBookingReadDtos(entity.getBookings()));

        return dto;
    }

    // Helper to convert Schedule entity to ScheduleReadDto
    default ScheduleReadDto toScheduleReadDto(Schedule schedule) {
        if (schedule == null)
            return null;
        ScheduleReadDto dto = new ScheduleReadDto();
        dto.setId(schedule.getId());
        dto.setDateTime(schedule.getDateTime());
        return dto;
    }

    // Helper to convert Material entities to MaterialReadDtos
    default List<MaterialReadDto> toMaterialReadDtos(List<Material> materials) {
        if (materials == null)
            return null;
        return materials.stream()
                .filter(Objects::nonNull)
                .map(m -> {
                    MaterialReadDto dto = new MaterialReadDto();
                    dto.setId(m.getId());
                    dto.setName(m.getName());
                    dto.setType(m.getType());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Helper to convert Booking entities to BookingReadDtos (without session to
    // avoid circular ref)
    default List<BookingReadDto> toBookingReadDtos(List<Booking> bookings) {
        if (bookings == null)
            return null;
        return bookings.stream()
                .filter(Objects::nonNull)
                .map(b -> {
                    BookingReadDto dto = new BookingReadDto();
                    dto.setId(b.getId());
                    dto.setDateTime(b.getDateTime());
                    dto.setStudentId(b.getStudentId());
                    dto.setSessionId(b.getSession() != null ? b.getSession().getId() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default void update(SessionUpdateDto dto, @MappingTarget Session entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getDuration() != null)
            entity.setDuration(dto.getDuration());
        if (dto.getTutorId() != null)
            entity.setTutorId(dto.getTutorId());
        if (dto.getScheduleId() != null) {
            Schedule sch = new Schedule();
            sch.setId(dto.getScheduleId());
            entity.setSchedule(sch);
        }
        if (dto.getMaterialIds() != null) {
            entity.setMaterials(mapMaterialIds(dto.getMaterialIds()));
        }
    }

    // Helpers for id<->entity mapping
    default Schedule map(Long id) {
        if (id == null)
            return null;
        Schedule s = new Schedule();
        s.setId(id);
        return s;
    }

    default Material mapMaterial(Long id) {
        if (id == null)
            return null;
        Material m = new Material();
        m.setId(id);
        return m;
    }

    default List<Material> mapMaterialIds(List<Long> ids) {
        if (ids == null)
            return null;
        List<Material> list = new ArrayList<>();
        for (Long id : ids) {
            if (id != null)
                list.add(mapMaterial(id));
        }
        return list;
    }
}
