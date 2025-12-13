package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.domain.entity.Qualification;
import com.onlinetutoring.usersservice.mapper.QualificationMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceQualificationImpl;
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
class QualificationControllerTest {

    @Mock
    private ServiceQualificationImpl serviceQualification;

    @Mock
    private QualificationMapper qualificationMapper;

    @InjectMocks
    private QualificationController qualificationController;

    private Qualification testQualification;
    private QualificationReadDto testReadDto;

    @BeforeEach
    void setUp() {
        testQualification = new Qualification();
        testQualification.setId(1L);
        testQualification.setTitle("PhD in Computer Science");
        testQualification.setInstitution("MIT");

        testReadDto = new QualificationReadDto();
        testReadDto.setId(1L);
        testReadDto.setTitle("PhD in Computer Science");
    }

    @Test
    void getAllQualifications_ShouldReturnList() {
        when(serviceQualification.getAllQualifications()).thenReturn(List.of(testQualification));
        when(qualificationMapper.toReadDto(any(Qualification.class))).thenReturn(testReadDto);

        ResponseEntity<List<QualificationReadDto>> response = qualificationController.getAllQualifications();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getQualificationById_ShouldReturnQualification() {
        when(serviceQualification.getQualificationById(1L)).thenReturn(testQualification);
        when(qualificationMapper.toReadDto(testQualification)).thenReturn(testReadDto);

        ResponseEntity<QualificationReadDto> response = qualificationController.getQualificationById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("PhD in Computer Science", response.getBody().getTitle());
    }

    @Test
    void createQualification_ShouldReturnCreatedQualification() {
        QualificationCreateUpdateDto createDto = new QualificationCreateUpdateDto("PhD in CS", "MIT");
        when(qualificationMapper.toEntity(any())).thenReturn(testQualification);
        when(serviceQualification.createQualification(any())).thenReturn(testQualification);
        when(qualificationMapper.toReadDto(any(Qualification.class))).thenReturn(testReadDto);

        ResponseEntity<QualificationReadDto> response = qualificationController.createQualification(createDto);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void deleteQualification_ShouldReturnSuccessMessage() {
        doNothing().when(serviceQualification).deleteQualification(1L);

        ResponseEntity<String> response = qualificationController.deleteQualification(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("successfully deleted"));
    }
}
