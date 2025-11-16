package com.onlinetutoring.evaluationservice.service;

import com.onlinetutoring.evaluationservice.domain.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedbacks();
}

