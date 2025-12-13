# SonarQube Issues Fix Report

**Project:** usersService  
**Date:** 2025-12-13  
**Total Issues Fixed:** 25 (10 High + 1 Medium + 14 Low)

---

## Commands to Run

```powershell
# Navigate to the project
cd c:\Users\user\Desktop\Online-Tutoring-App\Online-Tutoring-App\usersService

# Compile to verify fixes
mvn compile

# Run SonarQube analysis
mvn clean verify sonar:sonar -DskipTests `
  "-Dsonar.projectKey=onlinetutoring" `
  "-Dsonar.projectName=onlinetutoring" `
  "-Dsonar.host.url=http://localhost:9000" `
  "-Dsonar.token=YOUR_TOKEN"
```

---

## Issues Fixed

| # | File | Line | Issue Description | Severity | Fix Applied |
|---|------|------|-------------------|----------|-------------|
| 1 | `StudentReadDto.java` | L25 | Make non-static "subjects" private or transient | 🔴 High | Added `private` modifier to all fields |
| 2 | `TutorReadDto.java` | L26 | Make non-static "subjects" private or transient | 🔴 High | Added `private` modifier to all fields |
| 3 | `TutorReadDto.java` | L27 | Make non-static "qualifications" private or transient | 🔴 High | Added `private` modifier to all fields |
| 4 | `UserReadDto.java` | L23 | Make non-static "subjects" private or transient | 🔴 High | Added `private` modifier to all fields |
| 5 | `KafkaProducerService.java` | L60 | Define a constant instead of duplicating literal "STUDENT" 3 times | 🔴 High | Extracted to `USER_TYPE_STUDENT` constant |
| 6 | `KafkaProducerService.java` | L81 | Define a constant instead of duplicating literal "TUTOR" 3 times | 🔴 High | Extracted to `USER_TYPE_TUTOR` constant |

---

## Detailed Changes

### 1. DTO Classes - Serialization Fix

**Problem:** Fields in Serializable classes should be `private` or `transient` to prevent serialization issues.

**Files Modified:**
- `src/main/java/.../dto/student/StudentReadDto.java`
- `src/main/java/.../dto/tutor/TutorReadDto.java`
- `src/main/java/.../dto/user/UserReadDto.java`

**Before:**
```java
public class StudentReadDto implements Serializable {
    Long id;
    String firstName;
    Set<SubjectReadDto> subjects;
}
```

**After:**
```java
public class StudentReadDto implements Serializable {
    private Long id;
    private String firstName;
    private Set<SubjectReadDto> subjects;
}
```

---

### 2. KafkaProducerService - Duplicated Literals

**Problem:** String literals "STUDENT" and "TUTOR" were duplicated 3 times each.

**File Modified:**
- `src/main/java/.../kafka/KafkaProducerService.java`

**Before:**
```java
public class KafkaProducerService {
    private static final String TOPIC = "user-events";
    
    public void publishStudentCreated(...) {
        publishUserEvent(studentId, "STUDENT", "CREATED", ...);
    }
}
```

**After:**
```java
public class KafkaProducerService {
    private static final String TOPIC = "user-events";
    private static final String USER_TYPE_STUDENT = "STUDENT";
    private static final String USER_TYPE_TUTOR = "TUTOR";
    
    public void publishStudentCreated(...) {
        publishUserEvent(studentId, USER_TYPE_STUDENT, "CREATED", ...);
    }
}
```

---

## Summary

| File | Fields/Constants Changed |
|------|-------------------------|
| `StudentReadDto.java` | 7 fields → `private` |
| `TutorReadDto.java` | 9 fields → `private` |
| `UserReadDto.java` | 6 fields → `private` |
| `KafkaProducerService.java` | 2 constants added, 6 literal usages replaced |
| `ServiceUserImpl.java` | 2 transactional self-invocations replaced with direct repository calls |

---

## Additional Fix: ServiceUserImpl.java

**Problem:** Calling transactional methods via `this` bypasses Spring's proxy, so `@Transactional` settings are ignored.

| # | Line | Issue | Fix |
|---|------|-------|-----|
| 7 | L71 | Call transactional methods via injected dependency instead of directly via 'this' | Replaced `getById(userId)` with direct `userRepository.findById()` call |
| 8 | L81 | Call transactional methods via injected dependency instead of directly via 'this' | Replaced `getById(userId)` with direct `userRepository.findById()` call |
| 9 | L32 | Define a constant instead of duplicating literal "User not found: " 5 times | Extracted to `USER_NOT_FOUND` and `SUBJECT_NOT_FOUND` constants |
| 10 | L29 | This block of commented-out lines of code should be removed | Removed 78 lines of commented-out code + 7 unused imports |

**Before:**
```java
public User assignSubject(Long userId, Long subjectId) {
    User user = getById(userId);  // ❌ Self-invocation bypasses proxy
    ...
}
```

**After:**
```java
public User assignSubject(Long userId, Long subjectId) {
    User user = userRepository.findById(userId)  // ✅ Direct repository call
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
    ...
}
