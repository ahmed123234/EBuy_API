package com.example.simpleapi.services.reviews;

import com.example.simpleapi.entities.Review;
import com.example.simpleapi.services.product.ReviewDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    void createReview(Review review, HttpServletResponse response);

    List<ReviewDetails> displayAllReviews();

    void getReviewsByProduct(Integer productId, HttpServletResponse response);

//    ReviewResponse getReviewDetails(Integer reviewId);

    ResponseEntity<?> getReviewDetails(Integer reviewId);
}
