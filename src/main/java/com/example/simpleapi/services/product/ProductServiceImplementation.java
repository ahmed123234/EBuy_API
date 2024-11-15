package com.example.simpleapi.services.product;

import com.example.simpleapi.Repositories.product.ProductRepository;
import com.example.simpleapi.entities.product.Product;
import com.example.simpleapi.requestHandeling.ProductRequest;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImplementation implements  ProductService {

    private final ProductRepository productRepository;

    public void insertProduct (HttpServletResponse response, ProductRequest product) {

        productRepository.insertProduct(
                product.getProductName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImage(),
                product.getDepartment(),
//                product.getOfferId(),
                product.getRanking(),
                product.getUnitsInOrder(),
                product.getUnitsInStock()
//                product.getRate()
                );

        Map<String, String> body = new HashMap<>();

        body.put("message", "Product is created successfully");
        Response.setResponse(response,201, body );
    }
    @Override
    public void addProduct(HttpServletResponse response, ProductRequest product) {
        productRepository.save(Product.builder()
                        .category(product.getCategory())
                        .department(product.getDepartment())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .productName(product.getProductName())
                        .quantity(product.getQuantity())
                        .ranking(product.getRanking())
//                        .rate(product.getRate())
                        .image(product.getImage())
                        .unitsInOrder(product.getUnitsInOrder())
                        .unitsInStock(product.getUnitsInStock())
                        .images(product.getImages())
                .build());

        Map<String, String> body = new HashMap<>();

        body.put("message", "Product is created successfully");
        Response.setResponse(response,201, body );
    }

    @Override
    public List<Product> getAllProducts() {
        List <Product> products = productRepository.findAll();
//        List<ProductResponse> responseList = new ArrayList<>();

//        for (Product product : products) {
//            responseList.add(ProductResponse.builder()
//                            .departmentName(product.productDepartment())
//                            .category(product.getCategory())
//                            .description(product.getDescription())
//                            .image(product.getImage())
//                            .quantity(product.getQuantity())
//                            .unitsInStock(product.getUnitsInStock())
//                            .unitsInOrder(product.getUnitsInOrder())
//                            .ranking(product.getRanking())
//                            .rate(product.getRate())
//                            .price(product.getPrice())
//                            .productName(product.getProductName())
//                    .build());
//        }
        return products;
    }

    @Override
    public void getProduct(Integer productId, HttpServletResponse response) {
        Optional<Product> product = productRepository.findById(productId);

        // check if country is present
        Response.checkObjectAvailability(response, product,
                "No such product with the specified id %s", productId);

    }

    @Override
    public void getProductDepartment(Integer productId, HttpServletResponse response) {

        Optional<Product> product = productRepository.findById(productId);

        // check if product is present
        Response.checkObjectAvailability(response, product, productRepository.getProductDepartment(productId),
                "No such product with the specified id %s", productId);

    }

}

