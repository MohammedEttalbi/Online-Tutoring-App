package com.onlinetutoring.evaluationservice.service;


import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Feedback.FeedbackReadDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackReadDTO createFeedback(FeedbackCreateUpdateDTO dto);

    FeedbackReadDTO getFeedbackById(Long id);

    List<FeedbackReadDTO> getFeedbacksByReviewId(Long reviewId);

    List<FeedbackReadDTO> getAllFeedbacks();

    FeedbackReadDTO updateFeedback(Long id, FeedbackCreateUpdateDTO dto);

    void deleteFeedback(Long id);
}

