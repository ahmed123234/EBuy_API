package com.example.simpleapi.services.category;

import com.example.simpleapi.Repositories.category.CategoryRepository;
import com.example.simpleapi.controllers.category.CategoryRequest;
import com.example.simpleapi.entities.Category;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements  CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(HttpServletResponse response, CategoryRequest category) {
       Category savedCategory =  categoryRepository.save(
                Category.builder()
                        .name(category.getName())
                        .dailyProducts(category.getDailyProducts())
                        .description(category.getDescription())
                        .image(category.getImage())
                        .status(category.getStatus())
                        .build()
        );
        Response.setResponse(response,200, savedCategory);
    }

    @Override
    public void getCategories(HttpServletResponse response) {
        Response.setResponse(response,200, categoryRepository.findAll());
    }

    @Override
    public void getCategoryById(HttpServletResponse response, UUID categoryId) {
        Response.checkObjectAvailability(
                response,
                categoryRepository.findById(categoryId),
                "Category with id %s is not available",
                categoryId
                );
    }
}
