package com.eminimal.backend.repository;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByCategoryName(String name);

    Category findByCategoryID(String id);
}