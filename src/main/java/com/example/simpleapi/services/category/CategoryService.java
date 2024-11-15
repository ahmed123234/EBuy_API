package com.example.simpleapi.services.category;

import com.example.simpleapi.controllers.category.CategoryRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public interface CategoryService {
    void addCategory(HttpServletResponse response, CategoryRequest category);

    void getCategories(HttpServletResponse response);

    void getCategoryById(HttpServletResponse response, UUID categoryId);
}
