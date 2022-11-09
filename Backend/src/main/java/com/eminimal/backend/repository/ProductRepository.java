package com.eminimal.backend.repository;

import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContaining(String name);
    Product findByProductID(String id);

    List<Product> findByCategories_CategoryID(String categoryID);

    List<Product> findByCategories_CategoryName(String name);

    List<Product> findByOrderByProductSaleDesc();

    List<Product> findByOrderByProductCostDesc();

    List<Product> findByOrderByProductCostAsc();

    List<Product> findByOrderByProductNameDesc();

    List<Product> findByOrderByProductNameAsc();


}