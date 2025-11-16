package com.onlinetutoring.evaluationservice.controller;

import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingCreateUpdateDto;
import com.onlinetutoring.evaluationservice.domain.dto.Rating.RatingReadDto;
import com.onlinetutoring.evaluationservice.domain.entity.Rating;
import com.onlinetutoring.evaluationservice.mapper.RatingMapper;
import com.onlinetutoring.evaluationservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @GetMapping
    public List<RatingReadDto> getAll() {
        return ratingService.getAllRatings()
                .stream()
                .map(ratingMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public RatingReadDto getOne(@PathVariable Long id) {
        return ratingMapper.toReadDto(ratingService.getRatingById(id));
    }

    @PostMapping
    public RatingReadDto create(@RequestBody RatingCreateUpdateDto dto) {
        Rating rating = ratingMapper.toEntity(dto);
        Rating saved = ratingService.createRating(rating);
        return ratingMapper.toReadDto(saved);
    }

    @PutMapping("/{id}")
    public RatingReadDto update(@PathVariable Long id, @RequestBody RatingCreateUpdateDto dto) {
        Rating rating = ratingMapper.toEntity(dto);
        Rating updated = ratingService.updateRating(id, rating);
        return ratingMapper.toReadDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
