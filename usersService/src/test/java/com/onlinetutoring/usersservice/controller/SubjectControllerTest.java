package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.domain.entity.Subject;
import com.onlinetutoring.usersservice.mapper.SubjectMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceSubjectImpl;
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
class SubjectControllerTest {

    @Mock
    private ServiceSubjectImpl serviceSubject;

    @Mock
    private SubjectMapper subjectMapper;

    @InjectMocks
    private SubjectController subjectController;

    private Subject testSubject;
    private SubjectReadDto testReadDto;

    @BeforeEach
    void setUp() {
        testSubject = new Subject();
        testSubject.setId(1L);
        testSubject.setName("Mathematics");

        testReadDto = new SubjectReadDto();
        testReadDto.setId(1L);
        testReadDto.setName("Mathematics");
    }

    @Test
    void getAllSubjects_ShouldReturnList() {
        when(serviceSubject.getAllSubjects()).thenReturn(List.of(testSubject));
        when(subjectMapper.toReadDto(any(Subject.class))).thenReturn(testReadDto);

        ResponseEntity<List<SubjectReadDto>> response = subjectController.getAllSubjects();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getSubjectById_ShouldReturnSubject() {
        when(serviceSubject.getSubjectById(1L)).thenReturn(testSubject);
        when(subjectMapper.toReadDto(testSubject)).thenReturn(testReadDto);

        ResponseEntity<SubjectReadDto> response = subjectController.getSubjectById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Mathematics", response.getBody().getName());
    }

    @Test
    void deleteSubject_ShouldReturnSuccessMessage() {
        doNothing().when(serviceSubject).deleteSubject(1L);

        ResponseEntity<String> response = subjectController.deleteSubject(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("successfully deleted"));
    }

    @Test
    void createSubject_ShouldReturnCreatedSubject() {
        com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto createDto = new com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto(
                "Mathematics", "Math description");

        when(subjectMapper.toEntity(any())).thenReturn(testSubject);
        when(serviceSubject.createSubject(any(Subject.class))).thenReturn(testSubject);
        when(subjectMapper.toReadDto(testSubject)).thenReturn(testReadDto);

        ResponseEntity<SubjectReadDto> response = subjectController.createSubject(createDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(serviceSubject).createSubject(any(Subject.class));
    }

    @Test
    void updateSubject_ShouldReturnUpdatedSubject() {
        com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto updateDto = new com.onlinetutoring.usersservice.domain.dto.subject.SubjectCreateUpdateDto(
                "Physics", "Physics description");

        when(serviceSubject.getSubjectById(1L)).thenReturn(testSubject);
        when(subjectMapper.partialUpdate(any(), any(Subject.class))).thenReturn(testSubject);
        when(serviceSubject.updateSubject(any(Subject.class))).thenReturn(testSubject);
        when(subjectMapper.toReadDto(testSubject)).thenReturn(testReadDto);

        ResponseEntity<SubjectReadDto> response = subjectController.updateSubject(1L, updateDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteSubject_NotFound_ShouldReturn404() {
        doThrow(new jakarta.persistence.EntityNotFoundException("Subject not found"))
                .when(serviceSubject).deleteSubject(99L);

        ResponseEntity<String> response = subjectController.deleteSubject(99L);

        assertEquals(404, response.getStatusCode().value());
    }
}
