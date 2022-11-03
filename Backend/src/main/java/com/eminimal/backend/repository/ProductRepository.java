package com.eminimal.backend.repository;

import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContaining(String name);
    Product findByProductID(String id);

    List<Product> findByDetails_Categories_CategoryID(String categoryID);

    List<Product> findByDetails_Categories_CategoryName(String name);

}