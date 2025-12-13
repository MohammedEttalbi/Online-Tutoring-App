package com.onlinetutoring.usersservice.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, UserEventDto> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Captor
    private ArgumentCaptor<String> topicCaptor;

    @Captor
    private ArgumentCaptor<String> keyCaptor;

    @Captor
    private ArgumentCaptor<UserEventDto> eventCaptor;

    private CompletableFuture<SendResult<String, UserEventDto>> future;

    @BeforeEach
    void setUp() {
        future = new CompletableFuture<>();
        when(kafkaTemplate.send(anyString(), anyString(), any(UserEventDto.class))).thenReturn(future);
    }

    @Test
    void publishStudentCreated_ShouldSendCreatedEvent() {
        kafkaProducerService.publishStudentCreated(1L, "John", "Doe", "john@test.com");

        verify(kafkaTemplate).send(topicCaptor.capture(), keyCaptor.capture(), eventCaptor.capture());

        assertEquals("user-events", topicCaptor.getValue());
        assertEquals("STUDENT-1", keyCaptor.getValue());
        assertEquals("CREATED", eventCaptor.getValue().getEventType());
        assertEquals("STUDENT", eventCaptor.getValue().getUserType());
    }

    @Test
    void publishStudentUpdated_ShouldSendUpdatedEvent() {
        kafkaProducerService.publishStudentUpdated(1L, "John", "Doe", "john@test.com");

        verify(kafkaTemplate).send(anyString(), anyString(), eventCaptor.capture());

        assertEquals("UPDATED", eventCaptor.getValue().getEventType());
    }

    @Test
    void publishStudentDeleted_ShouldSendDeletedEvent() {
        kafkaProducerService.publishStudentDeleted(1L);

        verify(kafkaTemplate).send(anyString(), anyString(), eventCaptor.capture());

        assertEquals("DELETED", eventCaptor.getValue().getEventType());
        assertNull(eventCaptor.getValue().getFirstName());
    }

    @Test
    void publishTutorCreated_ShouldSendCreatedEvent() {
        kafkaProducerService.publishTutorCreated(2L, "Jane", "Smith", "jane@test.com");

        verify(kafkaTemplate).send(topicCaptor.capture(), keyCaptor.capture(), eventCaptor.capture());

        assertEquals("TUTOR-2", keyCaptor.getValue());
        assertEquals("TUTOR", eventCaptor.getValue().getUserType());
    }

    @Test
    void publishTutorUpdated_ShouldSendUpdatedEvent() {
        kafkaProducerService.publishTutorUpdated(2L, "Jane", "Smith", "jane@test.com");

        verify(kafkaTemplate).send(anyString(), anyString(), eventCaptor.capture());

        assertEquals("UPDATED", eventCaptor.getValue().getEventType());
    }

    @Test
    void publishTutorDeleted_ShouldSendDeletedEvent() {
        kafkaProducerService.publishTutorDeleted(2L);

        verify(kafkaTemplate).send(anyString(), anyString(), eventCaptor.capture());

        assertEquals("DELETED", eventCaptor.getValue().getEventType());
    }
}
