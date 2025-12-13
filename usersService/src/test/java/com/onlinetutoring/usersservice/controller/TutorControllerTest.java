package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.tutor.TutorReadDto;
import com.onlinetutoring.usersservice.domain.entity.Tutor;
import com.onlinetutoring.usersservice.mapper.TutorMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceTutorImpl;
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
class TutorControllerTest {

    @Mock
    private ServiceTutorImpl serviceTutor;

    @Mock
    private TutorMapper tutorMapper;

    @InjectMocks
    private TutorController tutorController;

    private Tutor testTutor;
    private TutorReadDto testReadDto;

    @BeforeEach
    void setUp() {
        testTutor = new Tutor();
        testTutor.setId(1L);
        testTutor.setFirstName("Bob");
        testTutor.setLastName("Teacher");
        testTutor.setBio("Experienced tutor");

        testReadDto = new TutorReadDto();
        testReadDto.setId(1L);
        testReadDto.setFirstName("Bob");
    }

    @Test
    void getAllTutors_ShouldReturnList() {
        when(serviceTutor.getAllTutors()).thenReturn(List.of(testTutor));
        when(tutorMapper.toReadDto(any(Tutor.class))).thenReturn(testReadDto);

        ResponseEntity<List<TutorReadDto>> response = tutorController.getAllTutors();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getTutorById_ShouldReturnTutor() {
        when(serviceTutor.getTutorById(1L)).thenReturn(testTutor);
        when(tutorMapper.toReadDto(testTutor)).thenReturn(testReadDto);

        ResponseEntity<TutorReadDto> response = tutorController.getTutorById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bob", response.getBody().getFirstName());
    }

    @Test
    void deleteTutor_ShouldReturnSuccessMessage() {
        doNothing().when(serviceTutor).deleteTutor(1L);

        ResponseEntity<String> response = tutorController.deleteTutor(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("successfully deleted"));
    }

    @Test
    void createTutor_ShouldReturnCreatedTutor() {
        com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto createDto = new com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto(
                "Bob", "Teacher", "bob@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.TUTOR, "Experienced tutor");

        when(tutorMapper.toEntity(any())).thenReturn(testTutor);
        when(serviceTutor.createTutor(any(Tutor.class))).thenReturn(testTutor);
        when(tutorMapper.toReadDto(testTutor)).thenReturn(testReadDto);

        ResponseEntity<TutorReadDto> response = tutorController.createTutor(createDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(serviceTutor).createTutor(any(Tutor.class));
    }

    @Test
    void updateTutor_ShouldReturnUpdatedTutor() {
        com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto updateDto = new com.onlinetutoring.usersservice.domain.dto.tutor.TutorCreateUpdateDto(
                "Bob", "Updated", "bob@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.TUTOR, "Updated bio");

        when(serviceTutor.getTutorById(1L)).thenReturn(testTutor);
        when(tutorMapper.partialUpdate(any(), any(Tutor.class))).thenReturn(testTutor);
        when(serviceTutor.updateTutor(any(Tutor.class))).thenReturn(testTutor);
        when(tutorMapper.toReadDto(testTutor)).thenReturn(testReadDto);

        ResponseEntity<TutorReadDto> response = tutorController.updateTutor(1L, updateDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteTutor_NotFound_ShouldReturn404() {
        doThrow(new jakarta.persistence.EntityNotFoundException("Tutor not found"))
                .when(serviceTutor).deleteTutor(99L);

        ResponseEntity<String> response = tutorController.deleteTutor(99L);

        assertEquals(404, response.getStatusCode().value());
    }
}
