package com.example.simpleapi.entities.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DescriptiveImages {
    @Id
    @GeneratedValue()
    @Column(name = "descriptive_image_id")
    private Integer id;

    private String frontPicture;
    private String BackPicture;
    private String rightSidePicture;
    private String leftSidePicture;


//    @OneToOne()
//    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
//    private Product product;
}
