package com.onlinetutoring.usersservice.service.impl;

import com.onlinetutoring.usersservice.domain.entity.Subject;
import com.onlinetutoring.usersservice.domain.entity.User;
import com.onlinetutoring.usersservice.repository.SubjectRepository;
import com.onlinetutoring.usersservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceUserImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private ServiceUserImpl serviceUser;

    private User testUser;
    private Subject testSubject;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@test.com");
        testUser.setSubjects(new HashSet<>());

        testSubject = new Subject();
        testSubject.setId(1L);
        testSubject.setName("Mathematics");
    }

    @Test
    void create_ShouldSaveAndReturnUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = serviceUser.create(testUser);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userRepository).save(testUser);
    }

    @Test
    void getById_WhenUserExists_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User result = serviceUser.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_WhenUserNotExists_ShouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> serviceUser.getById(99L));
    }

    @Test
    void getAll_ShouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        List<User> result = serviceUser.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void update_WhenUserExists_ShouldUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");
        updatedUser.setEmail("jane.doe@test.com");

        User result = serviceUser.update(updatedUser);

        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void update_WhenIdIsNull_ShouldThrowIllegalArgumentException() {
        User userWithoutId = new User();
        userWithoutId.setFirstName("Test");

        assertThrows(IllegalArgumentException.class, () -> serviceUser.update(userWithoutId));
    }

    @Test
    void delete_WhenUserExists_ShouldDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        serviceUser.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void delete_WhenUserNotExists_ShouldThrowException() {
        when(userRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> serviceUser.delete(99L));
    }

    @Test
    void assignSubject_WhenBothExist_ShouldAssignSubject() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(testSubject));

        User result = serviceUser.assignSubject(1L, 1L);

        assertTrue(result.getSubjects().contains(testSubject));
    }

    @Test
    void removeSubject_WhenBothExist_ShouldRemoveSubject() {
        testUser.getSubjects().add(testSubject);
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(testSubject));

        User result = serviceUser.removeSubject(1L, 1L);

        assertFalse(result.getSubjects().contains(testSubject));
    }
}
