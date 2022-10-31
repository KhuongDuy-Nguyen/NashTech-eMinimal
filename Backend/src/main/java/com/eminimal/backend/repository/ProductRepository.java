package com.eminimal.backend.repository;

import com.eminimal.backend.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContaining(String name);
    Product findByProductID(String id);
}