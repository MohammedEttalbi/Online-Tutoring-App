package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.student.StudentCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.student.StudentReadDto;
import com.onlinetutoring.usersservice.domain.entity.Student;
import com.onlinetutoring.usersservice.mapper.StudentMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceStudentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private ServiceStudentImpl serviceStudent;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentController studentController;

    private Student testStudent;
    private StudentReadDto testReadDto;

    @BeforeEach
    void setUp() {
        testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setFirstName("Alice");
        testStudent.setLastName("Smith");
        testStudent.setLevel("Undergraduate");

        testReadDto = new StudentReadDto();
        testReadDto.setId(1L);
        testReadDto.setFirstName("Alice");
    }

    @Test
    void getAllStudents_ShouldReturnList() {
        when(serviceStudent.getAllStudents()).thenReturn(List.of(testStudent));
        when(studentMapper.toReadDto(any(Student.class))).thenReturn(testReadDto);

        ResponseEntity<List<StudentReadDto>> response = studentController.getAllStudents();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getStudentById_ShouldReturnStudent() {
        when(serviceStudent.getStudentById(1L)).thenReturn(testStudent);
        when(studentMapper.toReadDto(testStudent)).thenReturn(testReadDto);

        ResponseEntity<StudentReadDto> response = studentController.getStudentById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Alice", response.getBody().getFirstName());
    }

    @Test
    void deleteStudent_ShouldReturnSuccessMessage() {
        doNothing().when(serviceStudent).deleteStudent(1L);

        ResponseEntity<String> response = studentController.deleteStudent(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("successfully deleted"));
    }

    @Test
    void createStudent_ShouldReturnCreatedStudent() {
        StudentCreateUpdateDto createDto = new StudentCreateUpdateDto(
                "Alice", "Smith", "alice@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.STUDENT, "Undergraduate");

        when(studentMapper.toEntity(any(StudentCreateUpdateDto.class))).thenReturn(testStudent);
        when(serviceStudent.createStudent(any(Student.class))).thenReturn(testStudent);
        when(studentMapper.toReadDto(testStudent)).thenReturn(testReadDto);

        ResponseEntity<StudentReadDto> response = studentController.createStudent(createDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(serviceStudent).createStudent(any(Student.class));
    }

    @Test
    void updateStudent_ShouldReturnUpdatedStudent() {
        StudentCreateUpdateDto updateDto = new StudentCreateUpdateDto(
                "Alice", "Updated", "alice@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.STUDENT, "Graduate");

        when(serviceStudent.getStudentById(1L)).thenReturn(testStudent);
        when(studentMapper.partialUpdate(any(StudentCreateUpdateDto.class), any(Student.class)))
                .thenReturn(testStudent);
        when(serviceStudent.updateStudent(any(Student.class))).thenReturn(testStudent);
        when(studentMapper.toReadDto(testStudent)).thenReturn(testReadDto);

        ResponseEntity<StudentReadDto> response = studentController.updateStudent(1L, updateDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteStudent_NotFound_ShouldReturn404() {
        doThrow(new jakarta.persistence.EntityNotFoundException("Student not found"))
                .when(serviceStudent).deleteStudent(99L);

        ResponseEntity<String> response = studentController.deleteStudent(99L);

        assertEquals(404, response.getStatusCode().value());
    }
}
