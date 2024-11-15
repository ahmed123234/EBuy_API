package com.example.simpleapi.services.category;

import com.example.simpleapi.Repositories.category.SubCategoryRepository;
import com.example.simpleapi.controllers.category.SubCategoryRequest;
import com.example.simpleapi.entities.SubCategory;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImplementation implements SubCategoryService {
    private final SubCategoryRepository categoryRepository;

    @Override
    public void addSubCategory(HttpServletResponse response, SubCategoryRequest subCategory) {

        Response.setResponse(
                response,
                200,
                categoryRepository.save(
                        SubCategory.builder()
                                .image(subCategory.getImage())
                                .category(subCategory.getCategory())
                                .name(subCategory.getName())
                                .description(subCategory.getDescription())                        .build()
                )
        );
    }

    @Override
    public void getSubCategories(HttpServletResponse response) {
        Response.setResponse(response,200, categoryRepository.findAll());
    }

    @Override
    public void getSubCategoryById(HttpServletResponse response, UUID subCategoryId) {
        Response.checkObjectAvailability(
                response,
                categoryRepository.findById(subCategoryId),
                "Category with id %s is not available",
                subCategoryId
        );
    }
}
