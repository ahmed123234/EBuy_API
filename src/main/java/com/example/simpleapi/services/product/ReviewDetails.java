package com.example.simpleapi.services.product;

/*
    using interface Spring data projection to customise the query result
    to a user defined object insted of generic purpose object (Object)

 */
public interface ReviewDetails {
    Integer getReviewId();
    String getReviewDescription();
    Double getReviewRating();
    String getAppUsername();
    String getProductName();
}
