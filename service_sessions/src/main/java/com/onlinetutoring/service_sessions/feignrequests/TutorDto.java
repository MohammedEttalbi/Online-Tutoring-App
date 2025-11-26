package com.onlinetutoring.service_sessions.feignrequests;

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
