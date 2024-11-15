package com.example.simpleapi.entities;

import com.example.simpleapi.entities.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private Integer offerId;
    @OneToMany(mappedBy = "offer")
    private List<Product> product;

    @OneToOne()
    @JoinColumn(name = "discount-id", referencedColumnName = "discount_id")
    private Discount discount;
}
