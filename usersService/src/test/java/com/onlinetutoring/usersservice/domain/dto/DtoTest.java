package com.onlinetutoring.usersservice.domain.dto;

import com.onlinetutoring.usersservice.domain.dto.student.StudentReadDto;
import com.onlinetutoring.usersservice.domain.dto.tutor.TutorReadDto;
import com.onlinetutoring.usersservice.domain.dto.user.UserReadDto;
import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.domain.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for DTO classes to improve code coverage of Lombok-generated methods.
 */
class DtoTest {

    // ==================== StudentReadDto Tests ====================

    @Test
    void studentReadDto_AllArgsConstructor_ShouldSetAllFields() {
        Set<SubjectReadDto> subjects = new HashSet<>();

        StudentReadDto dto = new StudentReadDto(1L, "Alice", "Smith", "alice@test.com", Role.STUDENT, "Undergraduate",
                subjects);

        assertEquals(1L, dto.getId());
        assertEquals("Alice", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
        assertEquals("alice@test.com", dto.getEmail());
        assertEquals(Role.STUDENT, dto.getRole());
        assertEquals("Undergraduate", dto.getLevel());
        assertSame(subjects, dto.getSubjects());
    }

    @Test
    void studentReadDto_NoArgsConstructorAndSetters_ShouldWork() {
        StudentReadDto dto = new StudentReadDto();
        dto.setId(2L);
        dto.setFirstName("Bob");
        dto.setLastName("Jones");
        dto.setEmail("bob@test.com");
        dto.setRole(Role.STUDENT);
        dto.setLevel("Graduate");
        dto.setSubjects(new HashSet<>());

        assertEquals(2L, dto.getId());
        assertEquals("Bob", dto.getFirstName());
        assertEquals("Jones", dto.getLastName());
        assertEquals("bob@test.com", dto.getEmail());
        assertEquals(Role.STUDENT, dto.getRole());
        assertEquals("Graduate", dto.getLevel());
        assertNotNull(dto.getSubjects());
    }

    // ==================== TutorReadDto Tests ====================

    @Test
    void tutorReadDto_AllArgsConstructor_ShouldSetAllFields() {
        Set<SubjectReadDto> subjects = new HashSet<>();
        Set<QualificationReadDto> qualifications = new HashSet<>();

        TutorReadDto dto = new TutorReadDto(1L, "Charlie", "Tutor", "charlie@test.com", Role.TUTOR, "Expert in Math",
                subjects, qualifications);

        assertEquals(1L, dto.getId());
        assertEquals("Charlie", dto.getFirstName());
        assertEquals("Tutor", dto.getLastName());
        assertEquals("charlie@test.com", dto.getEmail());
        assertEquals(Role.TUTOR, dto.getRole());
        assertEquals("Expert in Math", dto.getBio());
        assertSame(subjects, dto.getSubjects());
        assertSame(qualifications, dto.getQualifications());
    }

    @Test
    void tutorReadDto_NoArgsConstructorAndSetters_ShouldWork() {
        TutorReadDto dto = new TutorReadDto();
        dto.setId(3L);
        dto.setFirstName("Diana");
        dto.setLastName("Teacher");
        dto.setEmail("diana@test.com");
        dto.setRole(Role.TUTOR);
        dto.setBio("Teaching expert");
        dto.setSubjects(new HashSet<>());
        dto.setQualifications(new HashSet<>());

        assertEquals(3L, dto.getId());
        assertEquals("Diana", dto.getFirstName());
        assertEquals("Teacher", dto.getLastName());
        assertEquals("diana@test.com", dto.getEmail());
        assertEquals(Role.TUTOR, dto.getRole());
        assertEquals("Teaching expert", dto.getBio());
        assertNotNull(dto.getSubjects());
        assertNotNull(dto.getQualifications());
    }

    // ==================== UserReadDto Tests ====================

    @Test
    void userReadDto_AllArgsConstructor_ShouldSetAllFields() {
        Set<SubjectReadDto> subjects = new HashSet<>();

        UserReadDto dto = new UserReadDto(1L, "Eve", "Admin", "eve@test.com", Role.STUDENT, subjects);

        assertEquals(1L, dto.getId());
        assertEquals("Eve", dto.getFirstName());
        assertEquals("Admin", dto.getLastName());
        assertEquals("eve@test.com", dto.getEmail());
        assertEquals(Role.STUDENT, dto.getRole());
        assertSame(subjects, dto.getSubjects());
    }

    @Test
    void userReadDto_NoArgsConstructorAndSetters_ShouldWork() {
        UserReadDto dto = new UserReadDto();
        dto.setId(4L);
        dto.setFirstName("Frank");
        dto.setLastName("User");
        dto.setEmail("frank@test.com");
        dto.setRole(Role.TUTOR);
        dto.setSubjects(new HashSet<>());

        assertEquals(4L, dto.getId());
        assertEquals("Frank", dto.getFirstName());
        assertEquals("User", dto.getLastName());
        assertEquals("frank@test.com", dto.getEmail());
        assertEquals(Role.TUTOR, dto.getRole());
        assertNotNull(dto.getSubjects());
    }

    // ==================== SubjectReadDto Tests ====================

    @Test
    void subjectReadDto_AllArgsConstructorAndGetters_ShouldWork() {
        SubjectReadDto dto = new SubjectReadDto();
        dto.setId(1L);
        dto.setName("Mathematics");

        assertEquals(1L, dto.getId());
        assertEquals("Mathematics", dto.getName());
    }

    // ==================== QualificationReadDto Tests ====================

    @Test
    void qualificationReadDto_AllArgsConstructorAndGetters_ShouldWork() {
        QualificationReadDto dto = new QualificationReadDto();
        dto.setId(1L);
        dto.setTitle("PhD");
        dto.setInstitution("MIT");

        assertEquals(1L, dto.getId());
        assertEquals("PhD", dto.getTitle());
        assertEquals("MIT", dto.getInstitution());
    }
}
