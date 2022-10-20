package com.eminimal.backend.services;

import com.eminimal.backend.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CategoryService {
    //  Find product
    List<Category> findAll();

    Category findById(UUID uuid);

    Optional<Category> findByName(String name);

    //  Create new product
    Category save(Category category);

    void deleteById(UUID uuid);

    //  Update product
    Category updateCategory(UUID id, Category newCategory);
}
