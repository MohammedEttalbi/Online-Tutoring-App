package com.onlinetutoring.evaluationservice.feignrequests;

import com.onlinetutoring.evaluationservice.feignrequests.TutorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usersService", contextId="tutorFeignRequest")
public interface TutorFeignRequest {
    @GetMapping("/tutors/{id}")
    ResponseEntity<TutorDto> getTutorById(@PathVariable Long id);

}
