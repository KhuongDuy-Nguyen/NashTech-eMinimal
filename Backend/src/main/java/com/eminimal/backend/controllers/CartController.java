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

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//   Find cart
    @GetMapping
    public List<CartDto> findAll() throws ExecutionException, InterruptedException {
        return service.findAll().stream().map(cart -> modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/user")
    public CartDto findByUserId(@RequestParam String userID) throws ExecutionException, InterruptedException {
        return modelMapper.map(service.findById(userID), CartDto.class);
    }

//   Crete new cart
    @PostMapping("")
    public ResponseEntity<?> save(@RequestParam String userID, @RequestParam String productID) throws Exception {
        return ResponseEntity.ok().body(service.save(userID, productID));
    }

//    Update cart
    @PutMapping("")
    public ResponseEntity<?> updateCart(@RequestBody Cart cart) throws ExecutionException, InterruptedException {
//        Cart cartRequest = modelMapper.map(cartDto, Cart.class);
        return ResponseEntity.ok().body(service.updateCart(cart));
    }

//  Delete product in cart and cart
    @DeleteMapping("/product")
    public ResponseEntity<?> deleteProduct(@RequestParam String cartID, @RequestParam String productID) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(service.deleteProductById(cartID, productID));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteCart(@RequestParam String cartID){
        return ResponseEntity.ok().body(service.deleteCartById(cartID));
    }
}
