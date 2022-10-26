package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CartDto;
import com.eminimal.backend.models.Cart;
import com.eminimal.backend.services.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartServiceImpl service;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//   Find cart
    @GetMapping("/all")
    public List<Cart> findAll() throws ExecutionException, InterruptedException {
        return service.findAll();
    }

    @GetMapping("")
    public List<Cart> findCartByUserID(@RequestParam String userID) throws ExecutionException, InterruptedException {
        return service.findCartByUserID(userID);
    }

//   Crete new cart
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestParam String userID) throws Exception {
        return ResponseEntity.ok().body(service.save(userID));
    }

//   Add product in cart
    @PutMapping("/addProduct")
    public ResponseEntity<?> addProductInCart(@RequestParam String productID, @RequestParam String cartID) throws Exception {
        return ResponseEntity.ok().body(service.addProductInCart(productID,cartID));
    }

//  Delete product in cart
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProductInCart(@RequestParam String productID, @RequestParam String cartID) throws Exception {
        return ResponseEntity.ok().body(service.deleteProductInCart(productID,cartID));
    }

//  Delete cart
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCart(@RequestParam String cartID){
        return ResponseEntity.ok().body(service.deleteCartById(cartID));
    }
}
