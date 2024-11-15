package com.example.simpleapi.controllers.category;

import com.example.simpleapi.entities.SubCategory;
import com.example.simpleapi.services.category.SubCategoryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sub-categories")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping("add")
    public void addCategory(HttpServletResponse response,
                            @RequestBody SubCategoryRequest subCategory) {
        subCategoryService.addSubCategory(response,subCategory);
    }


    @GetMapping("{subCategoryId}")
    public void getCategoryById(HttpServletResponse response,
                                @PathVariable("subCategoryId") UUID subCategoryId) {
        subCategoryService.getSubCategoryById(response, subCategoryId);
    }


    @GetMapping
    public void getCategoryById(HttpServletResponse response) {
        subCategoryService.getSubCategories(response);
    }

}
