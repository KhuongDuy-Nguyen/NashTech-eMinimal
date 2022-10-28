package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.ProductDto;
import com.eminimal.backend.models.product.Product;
import com.eminimal.backend.models.product.ProductDetails;
import com.eminimal.backend.services.impl.ProductServiceImpl;
import com.eminimal.backend.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;


    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    //   Get information
    @GetMapping("/all")
    List<Product> findAll(){
        return service.findAll();
    }

    @GetMapping("/details/all")
    List<ProductDetails> findAllProductDetails(){
        return service.findAllProductDetails();
    }

    @GetMapping("")
    ResponseEntity<?> findProductById(@RequestParam String id) throws Exception {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/")
    ResponseEntity<List<Product>> findProductByName(@RequestParam String name) throws Exception {
        List<Product> products = service.findByName(name);
        return ResponseEntity.ok().body(products);
    }


    //  Create product
    @PostMapping("/create")
    ResponseEntity<?> createProduct(@RequestBody Product product) throws Exception {
        return ResponseEntity.ok().body(service.save(product));
    }

    //  Update product
    @PutMapping("/update")
    ResponseEntity<?> updateProduct(@RequestBody Product newProduct) throws Exception {
        return ResponseEntity.ok().body(service.updateProduct(newProduct));
    }

//    Rating product
    @PutMapping("/rating")
    ResponseEntity<ProductDto> ratingProduct(@RequestParam String id, @RequestBody int rating) throws Exception {
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
