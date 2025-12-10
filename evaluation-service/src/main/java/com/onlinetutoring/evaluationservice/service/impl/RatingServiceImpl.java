package com.onlinetutoring.evaluationservice.service.impl;


import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingCreateUpdateDTO;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDTO;
import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import com.onlinetutoring.evaluationservice.domain.entity.Review;
import com.onlinetutoring.evaluationservice.exception.ResourceNotFoundException;
import com.onlinetutoring.evaluationservice.mapper.RatingMapper;
import com.onlinetutoring.evaluationservice.repository.RatingRepository;
import com.onlinetutoring.evaluationservice.repository.ReviewRepository;
import com.onlinetutoring.evaluationservice.service.RatingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ReviewRepository reviewRepository;
    private final RatingMapper ratingMapper;

    @Override
    public RatingReadDTO createRating(RatingCreateUpdateDTO dto) {

        Review review = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review not found with id " + dto.getReviewId()));

        // vérifier si une rating existe déjà (à cause du OneToOne)
        if (ratingRepository.findByReviewId(dto.getReviewId()) != null) {
            throw new IllegalStateException("Rating already exists for this review");
        }

        Rating rating = ratingMapper.toEntity(dto);
        rating.setReview(review);

        Rating saved = ratingRepository.save(rating);
        return ratingMapper.toReadDto(saved);
    }

    @Override
    public RatingReadDTO getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
        return ratingMapper.toReadDto(rating);
    }

    @Override
    public RatingReadDTO getRatingByReviewId(Long reviewId) {
        Rating rating = ratingRepository.findByReviewId(reviewId);
        if(rating == null) throw new ResourceNotFoundException("Rating not found for review id " + reviewId);
        return ratingMapper.toReadDto(rating);
    }

    @Override
    public List<RatingReadDTO> getAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(ratingMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Override
    public RatingReadDTO updateRating(Long id, RatingCreateUpdateDTO dto) {

        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));

        Review review = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review not found with id " + dto.getReviewId()));

        ratingMapper.partialUpdate(dto, rating);
        rating.setReview(review);

        return ratingMapper.toReadDto(ratingRepository.save(rating));
    }

    @Override
    @Transactional
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
        ratingRepository.delete(rating);
    }
}

