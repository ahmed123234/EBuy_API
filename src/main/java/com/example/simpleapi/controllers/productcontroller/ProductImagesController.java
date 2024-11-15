package com.example.simpleapi.controllers.productcontroller;

import com.example.simpleapi.entities.product.DescriptiveImages;
import com.example.simpleapi.services.product.ProductImagesServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/images")
@AllArgsConstructor
public class ProductImagesController {
    private final ProductImagesServiceImplementation imagesServiceImplementation;

    @PostMapping("add")
    public void addImages(@RequestBody DescriptiveImages images, HttpServletResponse response) {
        imagesServiceImplementation.addImage(images, response);
    }

    @GetMapping
    public ResponseEntity<List<DescriptiveImages>> getAllProducts() {
        return ResponseEntity.ok(imagesServiceImplementation.displayAllImage());
    }

    @GetMapping("{imageId}")
    public void getProduct(@PathVariable("imageId") Integer productId,
                           HttpServletResponse response) {
        imagesServiceImplementation.displayImage(productId, response);
    }
}
