package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.user.UserReadDto;
import com.onlinetutoring.usersservice.domain.entity.User;
import com.onlinetutoring.usersservice.mapper.UserMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceUserImpl;
import jakarta.persistence.EntityNotFoundException;
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
class UserControllerTest {

    @Mock
    private ServiceUserImpl serviceUser;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private User testUser;
    private UserReadDto testReadDto;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@test.com");

        testReadDto = new UserReadDto();
        testReadDto.setId(1L);
        testReadDto.setFirstName("John");
    }

    @Test
    void getAllUsers_ShouldReturnList() {
        when(serviceUser.getAll()).thenReturn(List.of(testUser));
        when(userMapper.toReadDto(any(User.class))).thenReturn(testReadDto);

        ResponseEntity<List<UserReadDto>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUserById_ShouldReturnUser() {
        when(serviceUser.getById(1L)).thenReturn(testUser);
        when(userMapper.toReadDto(testUser)).thenReturn(testReadDto);

        ResponseEntity<UserReadDto> response = userController.getUserById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void deleteUser_Success_ShouldReturnSuccessMessage() {
        doNothing().when(serviceUser).delete(1L);

        ResponseEntity<String> response = userController.deleteUser(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("successfully deleted"));
    }

    @Test
    void deleteUser_NotFound_ShouldReturn404() {
        doThrow(new EntityNotFoundException("User not found")).when(serviceUser).delete(99L);

        ResponseEntity<String> response = userController.deleteUser(99L);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto createDto = new com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto(
                "John", "Doe", "john@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.STUDENT);

        when(userMapper.toEntity(any())).thenReturn(testUser);
        when(serviceUser.create(any(User.class))).thenReturn(testUser);
        when(userMapper.toReadDto(testUser)).thenReturn(testReadDto);

        ResponseEntity<UserReadDto> response = userController.createUser(createDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(serviceUser).create(any(User.class));
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto updateDto = new com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto(
                "John", "Updated", "john@test.com",
                com.onlinetutoring.usersservice.domain.enums.Role.STUDENT);

        when(serviceUser.getById(1L)).thenReturn(testUser);
        when(userMapper.partialUpdate(any(), any(User.class))).thenReturn(testUser);
        when(serviceUser.update(any(User.class))).thenReturn(testUser);
        when(userMapper.toReadDto(testUser)).thenReturn(testReadDto);

        ResponseEntity<UserReadDto> response = userController.updateUser(1L, updateDto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
