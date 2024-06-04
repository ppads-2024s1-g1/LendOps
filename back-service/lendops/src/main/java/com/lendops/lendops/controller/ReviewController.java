package com.lendops.lendops.controller;

import com.lendops.lendops.dto.ReviewRequestDTO;
import com.lendops.lendops.orm.Review;
import com.lendops.lendops.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        Review createdReview = reviewService.createReview(reviewRequestDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<List<Review>> getAllReviewsForMedia(@PathVariable Long mediaId) {
        List<Review> reviews = reviewService.getAllReviewsForMedia(mediaId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getAllReviewsForUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getAllReviewsForUser(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAllUsers() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}