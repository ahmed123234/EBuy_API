package com.example.simpleapi.controllers.category;

import com.example.simpleapi.services.category.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("add")
    public void addCategory(HttpServletResponse response,
                            @RequestBody CategoryRequest category) {


        categoryService.addCategory(response,category);
    }


    @GetMapping("{categoryId}")
    public void getCategoryById(HttpServletResponse response,
                            @PathVariable("categoryId") UUID categoryId) {
        categoryService.getCategoryById(response, categoryId);
    }


    @GetMapping
    public void getCategoryById(HttpServletResponse response) {
        categoryService.getCategories(response);
    }

}
