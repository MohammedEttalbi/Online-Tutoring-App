package com.onlinetutoring.evaluationservice.feignrequests;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usersService", contextId="studentFeignRequest")
public interface StudentFeignRequest {
    @GetMapping("/students/{id}")
    ResponseEntity<StudentDto> getStudentById(@PathVariable Long id);

}