package com.eminimal.backend.repository;

import com.eminimal.backend.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContaining(String name);
    Product findByProductID(String id);
}