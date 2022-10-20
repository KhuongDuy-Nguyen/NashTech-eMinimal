package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ProductServiceImpl implements com.eminimal.backend.services.ProductService {

    @Autowired
    private ProductRepository repository;

//  Find product
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(UUID uuid) {
        return repository.findByProductID(uuid);
    }

    @Override
    public Optional<Product> findProductByProductName(String name) {
        return Optional.ofNullable(repository.findByProductName(name));
    }

//  Create product
    @Override
    public <S extends Product> S save(S entity) {
        return repository.save(entity);
    }

//  Delete product
    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

//  Update product
    @Override
    public Product updateProduct(UUID id, Product newProduct){
        Product product =  repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't found product with id:" + id));

        product.setProductName(newProduct.getProductName());
        product.setProductDesc(newProduct.getProductDesc());
        product.setProductImage(newProduct.getProductImage());
        product.setProductCost(newProduct.getProductCost());

        product.setProductSale(newProduct.getProductSale());
        product.setProductRating(newProduct.getProductRating());
        product.setProductAmount(newProduct.getProductAmount());

        product.setDateUpdate(new Date());
        product.setDateSale(newProduct.getDateSale());

        product.setCategories(newProduct.getCategories());

        return repository.save(product);
    }

}
