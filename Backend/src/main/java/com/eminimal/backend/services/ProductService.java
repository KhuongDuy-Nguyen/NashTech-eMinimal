package com.eminimal.backend.services;

import com.eminimal.backend.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    //  Find product
    List<Product> findAll();

    Optional<Product> findById(UUID uuid);

    Optional<Product> findProductByProductName(String name);

    //  Create product
    <S extends Product> S save(S entity);

    //  Delete product
    void deleteById(UUID uuid);

    //  Update product
    Product updateProduct(UUID id, Product newProduct);
}
