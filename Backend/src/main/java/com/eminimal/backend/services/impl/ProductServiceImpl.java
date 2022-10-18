package com.eminimal.backend.services.impl;

import com.eminimal.backend.LoadDatabase;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements com.eminimal.backend.services.ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


//  Find product
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Optional<Product> findProductByProductName(String name) {
        return repository.findProductByProductName(name);
    }

//  Create product
    @Override
    public <S extends Product> S save(S entity) {
        Category category = entity.getCategories();
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
        return repository.findById(id).map(product -> {
            product = modelMapper.map(newProduct, Product.class);
            product.setProductID(id);
            product.setDateUpdate(new Date());
            return repository.save(product);
        }).orElseGet(() ->{
            newProduct.setProductID(id);
            return repository.save(newProduct);
        });
    }

}
