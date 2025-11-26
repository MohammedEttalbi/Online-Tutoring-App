package com.onlinetutoring.usersservice;

import com.onlinetutoring.usersservice.domain.entity.Qualification;
import com.onlinetutoring.usersservice.domain.entity.Student;
import com.onlinetutoring.usersservice.domain.entity.Subject;
import com.onlinetutoring.usersservice.domain.entity.Tutor;
import com.onlinetutoring.usersservice.domain.enums.Role;
import com.onlinetutoring.usersservice.service.Impl.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }
    @Bean
    ApplicationRunner init(ServiceStudentImpl studentService, ServiceTutorImpl tutorService, ServiceSubjectImpl serviceSubject, ServiceQualificationImpl serviceQualification) {
        return args -> {

//            Subject subject1 = new Subject();
//            subject1.setName("java j2e");
//            subject1.setDescription("java jee");
//            Subject subject2 = new Subject();
//            subject2.setName("deep learning");
//            subject2.setDescription("deep learning");
//            Subject subject3 = new Subject();
//            subject3.setName("Agentic AI");
//            subject3.setDescription("Mcp server");
//            serviceSubject.createSubject(subject1);
//            serviceSubject.createSubject(subject2);
//            serviceSubject.createSubject(subject3);
//            //----------------------------------------------------
//           Student student1 = new Student();
//           student1.setFirstName("Mohammed");
//           student1.setLastName("Ettalbi");
//           student1.setEmail("Mo@ett.com");
//           student1.setLevel("5IIR");
//           student1.setRole(Role.STUDENT);
//           student1.setSubjects(Set.of(subject1, subject2, subject3));
//           studentService.createStudent(student1);
//            Student student2 = new Student();
//            student2.setFirstName("Ahmed");
//            student2.setLastName("Ali");
//            student2.setLevel("5IIR");
//            student2.setEmail("ahmed@ali.com");
//            student2.setRole(Role.STUDENT);
//            student2.setSubjects(Set.of(subject1, subject2, subject3));
//            studentService.createStudent(student2);
//            Student student3 = new Student();
//            student3.setFirstName("Yassine");
//            student3.setLastName("Lewkili");
//            student3.setEmail("yas@lew.com");
//            student3.setLevel("5IIR");
//            student3.setRole(Role.STUDENT);
//            student3.setSubjects(Set.of(subject1, subject2, subject3));
//            studentService.createStudent(student3);
//            /*-----------------------------------------------*/
//            Tutor tutor1 = new Tutor();
//            tutor1.setFirstName("Salim");
//            tutor1.setLastName("Khalil");
//            tutor1.setEmail("SA@Kha.com");
//            tutor1.setBio("PHD Professor");
//            tutor1.setRole(Role.TUTOR);
//            tutor1.setSubjects(Set.of(subject1, subject2, subject3));
//            tutorService.createTutor(tutor1);
//            Tutor tutor2 = new Tutor();
//            tutor2.setFirstName("Mohammed");
//            tutor2.setLastName("Youssefi");
//            tutor2.setEmail("Mo@you.com");
//            tutor2.setBio("PHD Professor");
//            tutor2.setRole(Role.TUTOR);
//            tutor2.setSubjects(Set.of(subject1, subject2, subject3));
//            tutorService.createTutor(tutor2);
//            Tutor tutor3 = new Tutor();
//            tutor3.setFirstName("Mohammed");
//            tutor3.setLastName("Bousmah");
//            tutor3.setEmail("Mo@Bou.com");
//            tutor3.setBio("PHD Professor");
//            tutor3.setRole(Role.TUTOR);
//            tutor3.setSubjects(Set.of(subject1, subject2, subject3));
//            tutorService.createTutor(tutor3);
//
//            //----------------------------------------------
//
//            Qualification qualification1 = new Qualification();
//            qualification1.setTitle("PHD");
//            qualification1.setTutor(tutor1);
//            Qualification qualification2 = new Qualification();
//            qualification2.setTitle("Phd AI");
//            qualification2.setTutor(tutor2);
//            Qualification qualification3 = new Qualification();
//            qualification3.setTitle("PHD Mathematics");
//            qualification3.setTutor(tutor3);
//            serviceQualification.createQualification(qualification1);
//            serviceQualification.createQualification(qualification2);
//            serviceQualification.createQualification(qualification3);
//            //----------------------------------------------------

        };
    }

}
