package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.ProductDto;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.services.impl.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    //   Get information
    @GetMapping("/all")
    List<Product> findAll() throws ExecutionException, InterruptedException {
        return service.findAll();
    }

    @GetMapping("/search/id={id}")
    ResponseEntity<ProductDto> findProductById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Product product = service.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return  ResponseEntity.ok().body(productDto);
    }


    //  Create product
    @PostMapping("/create")
    String createProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return service.save(product);
    }

    //  Update product
    @PutMapping("/update")
    ResponseEntity<ProductDto> updateProduct( @RequestBody Product newProduct) throws ExecutionException, InterruptedException {
        String product = service.updateProduct(newProduct.getProductID(), newProduct);

//        Entity -> DTO
        ProductDto productResponse = modelMapper.map(product, ProductDto.class);

        return ResponseEntity.ok().body(productResponse);
    }

//    Rating product
    @PutMapping("/rating")
    ResponseEntity<ProductDto> ratingProduct(@RequestParam String id, @RequestBody int rating) throws ExecutionException, InterruptedException {
        Product product = service.ratingProduct(id, rating);
        ProductDto productResponse = modelMapper.map(product, ProductDto.class);
        return ResponseEntity.ok().body(productResponse);
    }

    //  Delete product
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteProductById(@RequestParam String id){
        service.deleteById(id);
        return new ResponseEntity<>("Remove successfully", HttpStatus.OK);
    }

}
