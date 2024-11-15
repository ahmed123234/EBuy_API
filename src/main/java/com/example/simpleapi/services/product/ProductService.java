package com.example.simpleapi.services.product;

import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.requestHandeling.ProductRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void addProduct(HttpServletResponse response, ProductRequest product);

    List<Product> getAllProducts();

    void getProduct(Integer productId, HttpServletResponse response);

    void getProductDepartment(Integer productId, HttpServletResponse response);
}
