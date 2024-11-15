package com.example.simpleapi.services.reviews;

public record ReviewResponse(Integer reviewId, String description, Double rating, String username, String productName) {
}
