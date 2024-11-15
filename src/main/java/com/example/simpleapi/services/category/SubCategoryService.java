package com.example.simpleapi.services.category;

import com.example.simpleapi.controllers.category.SubCategoryRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface SubCategoryService {

    void addSubCategory(HttpServletResponse response, SubCategoryRequest subCategory);

    void getSubCategories(HttpServletResponse response);

    void getSubCategoryById(HttpServletResponse response, UUID categoryId);
}
