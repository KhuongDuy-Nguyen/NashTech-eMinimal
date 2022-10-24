package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    //    Find all cart
    List<Cart> findAll();

    //    Find all cart with user ID
    Cart findById(String userID);

    //  Add item in order
    Cart save(String usersID, String productID);

    //    Update cart
    Cart updateCart(String id, Cart newCart);

    //  Delete item in order
    void deleteProductById(String cartID, String productID);

    void deleteCartById(String id);
}
