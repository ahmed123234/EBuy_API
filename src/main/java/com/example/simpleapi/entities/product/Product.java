package com.example.simpleapi.entities.product;

import com.example.simpleapi.entities.Offer;
import com.example.simpleapi.entities.department.Department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue()
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;
    @Column(nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "offer_id")
    private Offer offer;


    @OneToOne()
    @JoinColumn(name = "descriptive_image_id" , referencedColumnName = "descriptive_image_id")
    private DescriptiveImages images;

    /*
    -
    @OneToMany(mappedBy = "product")
    private List<DescriptiveImages> images;

    @OneToMany(mappedBy = "product")
    private List<Reviews> reviews;
     */
}
