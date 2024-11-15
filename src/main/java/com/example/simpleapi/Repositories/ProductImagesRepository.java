package com.example.simpleapi.Repositories;

import com.example.simpleapi.entities.product.DescriptiveImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepository extends JpaRepository<DescriptiveImages, Integer> {
}
