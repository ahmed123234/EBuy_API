package com.example.simpleapi.responsehandeling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    @SuppressWarnings("unused")
    private String departmentName;
    //    private Integer offer;
    private String category;
    private String productName;
    private Long quantity;
    private String image;
    private Long unitsInStock;
    private Long unitsInOrder;
    private Integer ranking;
    private Double rate;
    private Double price;
    private String description;
}
