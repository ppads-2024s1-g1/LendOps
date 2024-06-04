package com.lendops.lendops.service;

import com.lendops.lendops.dto.ReviewRequestDTO;
import com.lendops.lendops.orm.Media;
import com.lendops.lendops.orm.Review;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ReviewService {

    private final UserService userService;
    private final MediaService mediaService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(ReviewRequestDTO reviewDTO) {
        try {
            User user = userService.findUserById(reviewDTO.userId());
            Media media = mediaService.getMediaById(reviewDTO.mediaId());

            Review review = Review.builder()
                    .textReview(reviewDTO.textReview())
                    .title(reviewDTO.title())
                    .numbeOfStars(reviewDTO.numberOfStars())
                    .user(user)
                    .media(media)
                    .build();
            return reviewRepository.save(review);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Not possible to create media", e) {
            };
        }
    }

    public List<Review> getAllReviewsForMedia(Long mediaId) {
        try {
            return reviewRepository.findByMedia_Id(mediaId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Not possible to return medias", e) {
            };
        }
    }

    public void deleteReviewById(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ProviderNotFoundException("Review not found with id: " + reviewId);
        }

        reviewRepository.deleteById(reviewId);
    }

    public List<Review> getAllReviewsForUser(Long userId) {
        try {
            return reviewRepository.findByUser_Id(userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Not possible to return medias", e) {
            };
        }
    }

    public List<Review> getAllReviews() {
        Iterable<Review> reviews = reviewRepository.findAll();
        return StreamSupport.stream(reviews.spliterator(), false)
                .collect(Collectors.toList());
    }
}