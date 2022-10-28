package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Cart;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CartService {
    //    Find all cart
    List<Cart> findAll() throws ExecutionException, InterruptedException;

    //    Find cart of user ID
    List<Cart> findCartByUserID(String userID);

    //  Find cart ID
    Cart findByID(String cartID) throws Exception;

    //  Create cart
    Cart save(String userID) throws Exception;

    //  Add item in cart
    Cart addProductInCart(String productID, String cartID) throws Exception;

    //  Delete item in order
    Cart deleteProductInCart(String productID, String cartID) throws Exception;

    //  Delete cart
    String deleteCartById(String id);
}
