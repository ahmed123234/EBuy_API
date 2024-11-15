package com.example.simpleapi.requestHandeling;

import com.example.simpleapi.entities.Offer;
import com.example.simpleapi.entities.department.Department;
import com.example.simpleapi.entities.product.DescriptiveImages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRequest {
    private Department department;
    private Offer offer;
    private String category;
    private String productName;
    private Long quantity;
    private String image;
    private Long unitsInStock;
    private Long unitsInOrder;
    private Integer ranking;
//    private Double rate;
    private Double price;
    private String description;
    private DescriptiveImages images;
}
