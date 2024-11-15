package com.example.simpleapi.controllers.reviewcontroller;

import com.example.simpleapi.entities.Review;
import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.services.product.ReviewDetails;
import com.example.simpleapi.services.reviews.ReviewServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private final ReviewServiceImplementation reviewService;
    @PostMapping("add")
    public void createReview(@RequestBody ReviewRequest review, HttpServletResponse response) {
        reviewService.createReview(
                Review.builder()
                        .description(review.getDescription())
                        .rating(review.getRating())
                        .user(review.getUser())
                        .product(review.getProduct())
                        .build(), response);
    }

    //get all reviews for specific product
    @GetMapping("{productId}")
    public void getReviewPerProduct(@PathVariable("productId") Integer productId, HttpServletResponse response) {
         reviewService.getReviewsByProduct(productId, response);
    }

    // get all reviews in the database
    @GetMapping
    public ResponseEntity<List<ReviewDetails>> getReviewPerProduct() {
        return ResponseEntity.ok(reviewService.displayAllReviews());
    }

    //
    @GetMapping("getUsername")
    public void getReview(@RequestParam("reviewId") Integer reviewId,
                          HttpServletResponse response) {
        reviewService.getReviewUsername(reviewId, response);
    }

    // get review by reviewId
    @GetMapping("getReview")
    public ResponseEntity<?> getReviewDetails(@RequestParam("reviewId") Integer reviewId) {
//        ReviewResponse response = reviewService.getReviewDetails(reviewId);
//        return ResponseEntity.ok(response);
        return reviewService.getReviewDetails(reviewId);
    }

    @GetMapping("v1/getReview")
    public void getReviewDetails(@RequestParam("reviewId") Integer reviewId,
                                 HttpServletResponse response) {
         reviewService.getReviewDetails(reviewId, response);
    }



    @GetMapping("getProduct")
    public Product getReviewProduct(@RequestParam("reviewId") Integer reviewId) {
        return   reviewService.getReviewProduct(reviewId);
    }
}

@Data
class ReviewRequest {
   private String description;
   private Double rating;
   private User user;
   private Product product;
}


//        Map<String, Object> body = new HashMap<>();

//        body.put("review_id", response.reviewId());
//        body.put("description", response.description());
//        body.put("rating", response.rating());
//        body.put("user", response.username());
//        body.put("product", response.productName());