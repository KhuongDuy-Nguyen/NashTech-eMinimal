package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.CartRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.repository.UserRepository;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CartServiceImpl implements com.eminimal.backend.services.CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

//    Find all cart
    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

//    Find all cart with user ID
    @Override
    public Cart findById(UUID userID){
        return cartRepository.findByCartUsers_UserId(userID);
    }

//  Add item in order
    @Override
    public Cart save(UUID usersID, UUID productID){
        Users user = userRepository.findByUserId(usersID);
        Product product = productRepository.findByProductID(productID);
        Cart cartHaveUser = cartRepository.findByCartUsers_UserId(usersID);

        decreaseAmountProduct(productID);

        if(cartHaveUser == null){
            Cart cart = new Cart();
            cart.getCartProducts().add(product);
            cart.setCartQuantity(1);
            cart.setPrice(product.getProductCost());
            cart.setCartUsers(user);
            logger.info("Product in order: " + cart.getCartProducts());
            return cartRepository.save(cart);
        }else{
            cartHaveUser.getCartProducts().add(product);
            cartHaveUser.setCartQuantity(cartHaveUser.getCartQuantity() + 1);
            cartHaveUser.setPrice(cartHaveUser.getPrice() + product.getProductCost());
            logger.info("Product in order: " + cartHaveUser.getCartProducts());
            return cartRepository.save(cartHaveUser);
        }
    }

//    Change amount in product
    public void decreaseAmountProduct(UUID productID){
        Product product = productRepository.findByProductID(productID);
        product.setProductAmount(product.getProductAmount() - 1);
        productRepository.save(product);
    }

    public void increaseAmountProduct(UUID productID){
        Product product = productRepository.findByProductID(productID);
        product.setProductAmount(product.getProductAmount() + 1);
        productRepository.save(product);
    }

//    Update cart
    @Override
    public Cart updateCart(UUID id, Cart newCart){
        Cart cartDB = cartRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid cart id:" + id));

        cartDB.setPrice(newCart.getPrice());
        cartDB.setCartStatus(newCart.isCartStatus());
        cartDB.setCartProducts(newCart.getCartProducts());
        cartDB.setCartQuantity(newCart.getCartQuantity());

        return cartRepository.save(cartDB);
    }


    //  Delete item in order
    @Override
    public void deleteProductById(UUID cartID, UUID productID) {
        Cart cart = cartRepository.findByCartID(cartID);
        Product product = productRepository.findByProductID(productID);
        cart.getCartProducts().remove(product);
        increaseAmountProduct(productID);
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(UUID id){
        cartRepository.deleteById(id);
    }
}
