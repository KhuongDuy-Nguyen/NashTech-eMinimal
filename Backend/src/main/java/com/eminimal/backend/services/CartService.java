package com.eminimal.backend.services;

import com.eminimal.backend.models.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    //    Find all cart
    List<Cart> findAll();

    //    Find all cart with user ID
    Cart findById(UUID userID);

    //  Add item in order
    Cart save(UUID usersID, UUID productID);

    //    Update cart
    Cart updateCart(UUID id, Cart newCart);

    //  Delete item in order
    void deleteProductById(UUID cartID, UUID productID);

    void deleteCartById(UUID id);
}
