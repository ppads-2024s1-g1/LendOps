package com.lendops.lendops.dto;

public record ReviewRequestDTO(String title, String textReview, Integer numberOfStars, Long userId, Long mediaId) {
}
