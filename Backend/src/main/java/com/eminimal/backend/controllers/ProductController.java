package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.ErrorResponse;
import com.eminimal.backend.exceptions.ResourceFoundException;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Rating;
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


    @GetMapping("")
    ResponseEntity<?> findProductById(@RequestParam String id) throws Exception {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/search")
    ResponseEntity<List<Product>> findProductByName(@RequestParam String name) throws Exception {
        List<Product> products = service.findByName(name);
        return ResponseEntity.ok().body(products);
    }

//    Filter by category
    @GetMapping("/category")
    ResponseEntity<List<Product>> findByCategory(@RequestParam String name){
        return ResponseEntity.ok().body(service.findByCategory(name));
    }

    //    Order by cost - desc
    @GetMapping("/cost-desc")
    ResponseEntity<List<Product>> orderByCostDesc(){
        return ResponseEntity.ok().body(service.OrderProductCostDesc());
    }

    //    Order by cost - asc
    @GetMapping("/cost-asc")
    ResponseEntity<List<Product>> orderByCostAsc(){
        return ResponseEntity.ok().body(service.OrderProductCostAsc());
    }

    //    Order by name - desc
    @GetMapping("/name-desc")
    ResponseEntity<List<Product>> orderByNameDesc(){
        return ResponseEntity.ok().body(service.OrderProductNameDesc());
    }

    //    Order by name - asc
    @GetMapping("/name-asc")
    ResponseEntity<List<Product>> orderByNameAsc(){
        return ResponseEntity.ok().body(service.OrderProductNameAsc());
    }


    @GetMapping("/sale")
    ResponseEntity<List<Product>> getProductSale() throws Exception {
        return ResponseEntity.ok().body(service.getProductSale());
    }

    //  Create product
    @PostMapping(value ="/create")
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
    ResponseEntity<?> ratingProduct(@RequestParam String id, @RequestBody Rating rating) throws Exception {
        Product product = service.ratingProduct(id, rating);
//        ProductDto productResponse = modelMapper.map(product, ProductDto.class);
        return ResponseEntity.ok().body(product);
    }



    //  Delete product
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteProductById(@RequestParam String id){
        service.deleteById(id);
        return new ResponseEntity<>("Remove successfully", HttpStatus.OK);
    }

    @ExceptionHandler(ResourceFoundException.class)
    ResponseEntity<ErrorResponse> resourceFoundException(){
        ErrorResponse errorResponse = new ErrorResponse("03", "Something was wrong. Try login again");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
