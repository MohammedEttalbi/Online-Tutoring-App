package org.example.service_sessions.mapper;

import org.example.service_sessions.domain.dto.session.SessionCreateDto;
import org.example.service_sessions.domain.dto.session.SessionReadDto;
import org.example.service_sessions.domain.dto.session.SessionUpdateDto;
import org.example.service_sessions.domain.entity.Booking;
import org.example.service_sessions.domain.entity.Material;
import org.example.service_sessions.domain.entity.Schedule;
import org.example.service_sessions.domain.entity.Session;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {

    default Session toEntity(SessionCreateDto dto) {
        if (dto == null) return null;
        Session s = new Session();
        s.setName(dto.name);
        s.setDuration(dto.duration);
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
        if (entity == null) return null;
        SessionReadDto dto = new SessionReadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setScheduleId(entity.getSchedule() != null ? entity.getSchedule().getId() : null);
        dto.setMaterialIds(toIds(entity.getMaterials()));
        dto.setBookingIds(toBookingIds(entity.getBookings()));
        return dto;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default void update(SessionUpdateDto dto, @MappingTarget Session entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getDuration() != null) entity.setDuration(dto.getDuration());
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
        if (id == null) return null;
        Schedule s = new Schedule();
        s.setId(id);
        return s;
    }

    default Material mapMaterial(Long id) {
        if (id == null) return null;
        Material m = new Material();
        m.setId(id);
        return m;
    }

    default List<Material> mapMaterialIds(List<Long> ids) {
        if (ids == null) return null;
        List<Material> list = new ArrayList<>();
        for (Long id : ids) {
            if (id != null) list.add(mapMaterial(id));
        }
        return list;
    }

    default List<Long> toIds(List<Material> materials) {
        if (materials == null) return null;
        return materials.stream().filter(Objects::nonNull).map(Material::getId).collect(Collectors.toList());
    }

    default List<Long> toBookingIds(List<Booking> bookings) {
        if (bookings == null) return null;
        return bookings.stream().filter(Objects::nonNull).map(Booking::getId).collect(Collectors.toList());
    }
}

