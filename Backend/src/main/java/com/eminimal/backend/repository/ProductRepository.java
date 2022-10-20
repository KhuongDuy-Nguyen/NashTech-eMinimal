package com.eminimal.backend.repository;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByProductName(String name);
    Product findByProductID(UUID id);
}