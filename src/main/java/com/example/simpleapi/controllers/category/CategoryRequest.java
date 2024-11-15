package com.example.simpleapi.controllers.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private String description;
    private Boolean dailyProducts;
    private Boolean status;
    private String image;
}
