package com.eminimal.backend.controllers;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    //   Get information
    @GetMapping("/all")
    Iterable<Product> getAll(){
        return service.findAll();
    }

    @GetMapping("/id={id}")
    Optional<Product> getAllById(@PathVariable UUID id){
        return service.findById(id);
    }

    @PostMapping("/")
    Optional<Product> getAllByName(@RequestParam String name){
        return service.findProductByProductName(name);
    }

    //  Create product
    @PostMapping("")
    Product newProduct(@RequestBody Product newProduct){
        return service.save(newProduct);
    }

    //  Update product
    @PutMapping("/{id}")
    Product updateProduct(@PathVariable UUID id, @RequestBody Product newProduct){
        return service.updateProduct(id, newProduct);
    }

    //  Delete product
    @DeleteMapping("/{id}")
    void deleteAllProductById(@PathVariable UUID id){
        service.deleteById(id);
    }
}
