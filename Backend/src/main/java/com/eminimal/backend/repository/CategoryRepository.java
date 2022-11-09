package com.eminimal.backend.repository;

import com.eminimal.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByCategoryNameContaining(String name);

    Category findByCategoryName(String name);

    Category findByCategoryID(String id);

    Boolean existsByCategoryName(String name);
}