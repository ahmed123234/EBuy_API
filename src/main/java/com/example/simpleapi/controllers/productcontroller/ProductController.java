package com.example.simpleapi.controllers.productcontroller;

import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.requestHandeling.ProductRequest;
import com.example.simpleapi.services.product.ProductServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@Slf4j
public class ProductController {
    private final ProductServiceImplementation productServiceImplementation;

    @PostMapping("add")
    public void createProduct(@RequestBody ProductRequest request, HttpServletResponse response) {
        productServiceImplementation.addProduct(response, request);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
       return ResponseEntity.ok(productServiceImplementation.getAllProducts());
    }

    @GetMapping("{productId}")
    public void getProduct(@PathVariable("productId") Integer productId,
                                              HttpServletResponse response) {
        productServiceImplementation.getProduct(productId, response);
    }

    @GetMapping("get-department/{productId}")
    public void getProductDepartmentInfo(@PathVariable("productId") Integer productId,
                           HttpServletResponse response) {
       log.info("inside get department endpoint");
        productServiceImplementation.getProductDepartment(productId, response);
    }



/*
-
    @GetMapping
    public void getAllProducts(HttpServletResponse response) {

        Response.setResponse(response, 200,
                ResponseEntity.ok(productServiceImplementation.getAllProducts())

        );
   }
 */
}
