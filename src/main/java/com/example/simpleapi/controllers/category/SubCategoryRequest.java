package com.example.simpleapi.controllers.category;

import com.example.simpleapi.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryRequest {
    private String name;
    private String description;
    private String image;
    private Category category;
}
