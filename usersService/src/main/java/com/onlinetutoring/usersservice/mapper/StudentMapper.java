package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.StudentDto;
import com.onlinetutoring.usersservice.domain.entity.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    Student toEntity(StudentDto studentDto);

    StudentDto toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}