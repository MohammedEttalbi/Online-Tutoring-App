package com.onlinetutoring.evaluationservice.feignrequests;

import lombok.Data;

@Data
public class TutorDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    Role role;
    String bio;
}
