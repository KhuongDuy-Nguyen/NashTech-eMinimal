package com.eminimal.backend.repository;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findCategoryByCategoryName(String name);

    Category findCategoriesByCategoryName(String name);


}