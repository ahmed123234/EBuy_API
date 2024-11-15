package com.example.simpleapi.services.reviews;

import com.example.simpleapi.Repositories.ReviewRepository;
import com.example.simpleapi.entities.Review;
import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.responsehandeling.Response;
import com.example.simpleapi.services.product.ReviewDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImplementation implements ReviewService{
    private final ReviewRepository reviewRepository;
    @Override
    public void createReview(Review review, HttpServletResponse response) {
        reviewRepository.save(review);
        Map <String, String> body = new HashMap<>();
        body.put("message", "Review created successfully");
        Response.setResponse(response, 201, body);
    }

    @Override
    public List<ReviewDetails> displayAllReviews() {
        return reviewRepository.getAllReviews();
    }

    @Override
    public void getReviewsByProduct(Integer productId, HttpServletResponse response) {
        List<ReviewResponse> responseList = reviewRepository.getReviewsPerProduct(productId);
        if(responseList.size() == 0) {
            Map<String, String> body = new HashMap<>();
            log.info("No such revision for a product with Id {}", productId);
            body.put("error", String.format("No such revision for a product with Id %s", productId));
            Response.setResponse(response, 404, body);
        } else {
            log.info("Reviews are listed successfully");
            Response.setResponse(response, 200, responseList );
        }

    }

    public ResponseEntity<Review> getReview(Integer reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void getReviewUsername(Integer reviewId, HttpServletResponse response) {
        Map<String, String> res  = new HashMap<>();
        res.put("message", reviewRepository.getReviewInfo(reviewId));
        Response.setResponse(response, 200, res);
    }

    public Product getReviewProduct(Integer reviewId) {
        return reviewRepository.getReviewProduct(reviewId);
    }



    public void getReviewDetails(Integer reviewId, HttpServletResponse response) {

         Response.checkObjectAvailability(response, reviewRepository.getReview(reviewId),
                 "No such review with Id %", reviewId);
    }

    @Override
    public ResponseEntity<?> getReviewDetails(Integer reviewId) {

        return reviewRepository.getReview(reviewId)
                 .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    public ResponseEntity<?> getReviewDetails(Integer reviewId, HttpServletResponse response) {
//
//        Optional<ReviewResponse> reviewResponse = reviewRepository.getReviewW(reviewId);
//
//        Map<String, Object> body = new HashMap<>();
//
//        body.put("review_id", reviewResponse.get().getReviewId());
//        body.put("description", reviewResponse.get().getDescription());
//        body.put("rating", reviewResponse.get().getRating());
//        body.put("user", reviewResponse.get().getUsername());
//        body.put("product", reviewResponse.get().getProductName());
//        return ResponseEntity.ok(body);
//    }
}

