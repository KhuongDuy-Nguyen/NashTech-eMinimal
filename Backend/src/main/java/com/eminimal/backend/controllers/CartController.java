package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CartDto;
import com.eminimal.backend.models.Cart;
import com.eminimal.backend.services.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceImpl service;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//   Find cart
    @GetMapping
    List<CartDto> findAll(){
        return service.findAll().stream().map(cart -> modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/user")
    CartDto findByUserId(@RequestParam UUID userID){
        return modelMapper.map(service.findById(userID), CartDto.class);
    }

//   Crete new cart
    @PostMapping("")
    CartDto save(@RequestParam UUID userID, @RequestParam UUID productID){
        Cart cart = service.save(userID, productID);

        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        logger.info("Cart: " + cart);
        logger.info("CartDTO: " +  cartDto);
        return cartDto;
    }

//    Update cart
    @PutMapping("")
    CartDto updateCart(@RequestBody UUID cartID,@RequestBody CartDto cartDto){
        Cart cartRequest = modelMapper.map(cartDto, Cart.class);
        return modelMapper.map(service.updateCart(cartID, cartRequest), CartDto.class);
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
