package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ProductService {
    //  Find product
    List<Product> findAll() throws ExecutionException, InterruptedException;

    Product findById(String String) throws ExecutionException, InterruptedException;

    //  Create product
    <S extends Product> String save(S entity) throws ExecutionException, InterruptedException;

    //  Delete product
    String deleteById(String id);

    //  Update product
    String updateProduct(String id, Product newProduct) throws ExecutionException, InterruptedException;
}
