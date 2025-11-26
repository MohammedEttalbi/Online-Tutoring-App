package com.onlinetutoring.service_sessions;

import com.onlinetutoring.service_sessions.domain.entity.Material;
import com.onlinetutoring.service_sessions.domain.entity.Schedule;
import com.onlinetutoring.service_sessions.domain.entity.Session;
import com.onlinetutoring.service_sessions.repository.MaterialRepository;
import com.onlinetutoring.service_sessions.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceSessionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSessionsApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(SessionRepository sessionRepository,
                                   MaterialRepository materialRepository) {
        return args -> {
//            // Check if data already exists
//            if (sessionRepository.count() > 0) {
//                System.out.println("Database already initialized. Skipping test data creation.");
//                return;
//            }
//
//            // Create Materials
//            Material mathBook = new Material();
//            mathBook.setName("Advanced JEE");
//            mathBook.setType("PDF");
//            materialRepository.save(mathBook);
//
//            Material videoTutorial = new Material();
//            videoTutorial.setName("Algebra Video Tutorial");
//            videoTutorial.setType("Video");
//            materialRepository.save(videoTutorial);
//
//            Material physicsGuide = new Material();
//            physicsGuide.setName("C/C++ Study Guide");
//            physicsGuide.setType("PDF");
//            materialRepository.save(physicsGuide);
//
//            Material codingExercises = new Material();
//            codingExercises.setName("Java Programming Exercises");
//            codingExercises.setType("Document");
//            materialRepository.save(codingExercises);
//
//            // Create Session 1: Mathematics
//            Schedule schedule1 = new Schedule();
//            schedule1.setDateTime(LocalDateTime.now().plusDays(2).withHour(10).withMinute(0));
//
//            Session session1 = new Session();
//            session1.setName("Advanced JEE Session");
//            session1.setDuration(1.5);
//            session1.setTutorName("Dr. Salim Khalil");
//            session1.setSchedule(schedule1);
//            session1.setMaterials(Arrays.asList(mathBook, videoTutorial));
//            schedule1.setSession(session1);
//
//            sessionRepository.save(session1);
//            System.out.println("Created Session: " + session1.getName());
//
//            // Create Session 2: Physics
//            Schedule schedule2 = new Schedule();
//            schedule2.setDateTime(LocalDateTime.now().plusDays(3).withHour(14).withMinute(30));
//
//            Session session2 = new Session();
//            session2.setName("Quantum Physics Basics");
//            session2.setDuration(2.0);
//            session2.setTutorName("Prof. Mohammed bousmah");
//            session2.setSchedule(schedule2);
//            session2.setMaterials(Arrays.asList(physicsGuide));
//            schedule2.setSession(session2);
//
//            sessionRepository.save(session2);
//            System.out.println("Created Session: " + session2.getName());
//
//            // Create Session 3: Programming
//            Schedule schedule3 = new Schedule();
//            schedule3.setDateTime(LocalDateTime.now().plusDays(5).withHour(16).withMinute(0));
//
//            Session session3 = new Session();
//            session3.setName("Java Spring Boot Workshop");
//            session3.setDuration(3.0);
//            session3.setTutorName("Mohammed Youssfi");
//            session3.setSchedule(schedule3);
//            session3.setMaterials(Arrays.asList(codingExercises, videoTutorial));
//            schedule3.setSession(session3);
//
//            sessionRepository.save(session3);
//            System.out.println("Created Session: " + session3.getName());
//
//            System.out.println("Test data initialization completed successfully!");
//            System.out.println("Total Sessions: " + sessionRepository.count());
//            System.out.println("Total Materials: " + materialRepository.count());
        };
    }
}