package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Cart;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface CartService {
    //    Find all cart
    List<Cart> findAll() throws ExecutionException, InterruptedException;

    //    Find all cart with user ID
    Cart findById(String userID) throws ExecutionException, InterruptedException;

    //  Add item in order
    String save(String usersID, String productID) throws Exception;

    //    Update cart
    String updateCart(Cart newCart) throws ExecutionException, InterruptedException;

    //  Delete item in order
    String deleteProductById(String cartID, String productID) throws ExecutionException, InterruptedException;

    String deleteCartById(String id);
}
