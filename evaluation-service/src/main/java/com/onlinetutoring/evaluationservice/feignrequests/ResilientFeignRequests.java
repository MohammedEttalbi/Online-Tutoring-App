package com.onlinetutoring.evaluationservice.feignrequests;


import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ResilientFeignRequests {

    private final TutorFeignRequest tutorFeignRequest;
    private final StudentFeignRequest studentFeignRequest;

    public ResilientFeignRequests(TutorFeignRequest tutorFeignRequest,
                                  StudentFeignRequest studentFeignRequest) {
        this.tutorFeignRequest = tutorFeignRequest;
        this.studentFeignRequest = studentFeignRequest;
    }


    @CircuitBreaker(name = "usersService", fallbackMethod = "getTutorByIdFallback")
    @Retry(name = "users-service")
    public ResponseEntity<TutorDto> getTutorById(Long id) {
        try {
            return tutorFeignRequest.getTutorById(id);
        } catch (FeignException feignex) {
            throw feignex;
        }
    }


    @CircuitBreaker(name = "users-service", fallbackMethod = "getStudentByIdFallback")
    @Retry(name = "users-service")
    public ResponseEntity<StudentDto> getStudentById(Long id) {
        try {
            return studentFeignRequest.getStudentById(id);
        } catch (FeignException feignex) {
            throw feignex;
        }
    }

    /* ---------------------------
     * Fallbacks
     * Signature: (original params..., Throwable/Exception)
     * --------------------------- */
    private ResponseEntity<TutorDto> getTutorByIdFallback(Long id, Exception ex) {
        throw new RuntimeException("users-service (tutor) is not available");
    }

    private ResponseEntity<List<StudentDto>> getStudentsByIdsFallback(Set<Long> ids, Exception ex) {
        throw new RuntimeException("users-service (students) is not available");
    }
}
