package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.user.UserReadDto;
import com.onlinetutoring.usersservice.domain.entity.User;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCreateUpdateDto userCreateUpdateDto);

    UserCreateUpdateDto toDto(User user);

    Set<UserReadDto> toReadDtoSet(Set<User> docteurs);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserCreateUpdateDto userCreateUpdateDto, @MappingTarget User user);
}