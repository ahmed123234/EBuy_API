package com.example.simpleapi.Repositories;

import com.example.simpleapi.entities.Review;
import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.services.product.ReviewDetails;
import com.example.simpleapi.services.reviews.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
   @Query("select new com.example.simpleapi.services.reviews.ReviewResponse" +
           "(reviewId, description, rating, user.username, product.productName) " +
           "from Review  where product.productId = :productId")
   List<ReviewResponse> getReviewsPerProduct(Integer productId);


    @Query("select reviewId as reviewId, description as reviewDescription, rating as reviewRating," +
            " user.username as appUsername, product.productName as productName " +
            "from Review")
    List<ReviewDetails> getAllReviews();

   @Query("select user.username from Review where product.productId = ?1")
   String getReviewInfo(Integer productId);

   @Query("select product from Review where reviewId = ?1")
   Product getReviewProduct(Integer reviewId);


   @Query("select new  com.example.simpleapi.services.reviews.ReviewResponse" +
           "(reviewId, description, rating, user.username, product.productName) " +
           "from Review where reviewId = ?1")
   Optional<ReviewResponse> getReview(Integer reviewId);


   @Query("select new  com.example.simpleapi.services.reviews.ReviewResponse" +
           "(reviewId, description, rating, user.username, product.productName) " +
           "from Review where reviewId = ?1")
   Optional<ReviewResponse> getReviewW(Integer reviewId);


}
