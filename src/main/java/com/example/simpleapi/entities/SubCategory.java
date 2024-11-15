package com.example.simpleapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubCategory {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category-id", referencedColumnName = "category-id")
    private Category category;
}
