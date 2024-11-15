package com.example.simpleapi.Repositories.category;

import com.example.simpleapi.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
}
