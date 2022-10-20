package com.eminimal.backend.controllers;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.services.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceImpl service;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//   Find cart
    @GetMapping
    List<Cart> findAll(){
        return service.findAll();
    }

    @PostMapping("/user")
    Cart findByUserId(@RequestParam UUID userID){
        return service.findById(userID);
    }

//   Crete new cart
    @PostMapping("")
    Cart save(@RequestParam UUID userID, @RequestParam UUID productID){
        return service.save(userID, productID);
    }

//    Update cart
    @PutMapping("")
    Cart updateCart(@RequestBody UUID cartID,@RequestBody Cart cart){
        return service.updateCart(cartID, cart);
    }

//  Delete product in cart and cart
    @DeleteMapping("/product")
    void deleteProduct(@RequestParam UUID cartID, @RequestParam UUID productID){
        service.deleteProductById(cartID, productID);
    }

    @DeleteMapping("")
    void deleteCart(@RequestParam UUID cartID){
        service.deleteCartById(cartID);
    }
}
