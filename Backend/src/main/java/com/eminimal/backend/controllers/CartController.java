package com.eminimal.backend.controllers;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.services.impl.CartServiceImpl;
import com.eminimal.backend.services.interfaces.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService service;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//   Find cart
    @GetMapping("/all")
    public ResponseEntity<List<Cart>> findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("")
    public ResponseEntity<List<Cart>> findCartByUserID(@RequestParam String userID){
        return ResponseEntity.ok().body(service.findCartByUserID(userID));
    }

    @GetMapping("/id")
    public ResponseEntity<?> findCartByCartID(@RequestParam String cartID) throws Exception {
        return ResponseEntity.ok().body(service.findByID(cartID));
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<?> findCartWhenCartStatusIsFalse(@RequestParam String userId) throws Exception {
        return ResponseEntity.ok().body(service.findCartWhenStatusIsFalse(userId));
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
