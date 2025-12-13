package com.onlinetutoring.usersservice.mapper;

import com.onlinetutoring.usersservice.domain.dto.student.StudentCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.student.StudentReadDto;
import com.onlinetutoring.usersservice.domain.entity.Student;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        SubjectMapper.class })
public interface StudentMapper {
    Student toEntity(StudentCreateUpdateDto studentCreateUpdateDto);

    StudentReadDto toReadDto(Student student);

    Set<StudentReadDto> toReadDtoSet(Set<Student> students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentCreateUpdateDto studentCreateUpdateDto, @MappingTarget Student student);
}