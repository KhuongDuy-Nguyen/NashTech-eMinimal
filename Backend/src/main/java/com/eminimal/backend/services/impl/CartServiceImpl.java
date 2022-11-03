package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.CartRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.interfaces.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;



//    Find all cart
    @Override
    public List<Cart> findAll() throws ExecutionException, InterruptedException {
        return cartRepository.findAll();
    }

    //    Find cart of user ID
    @Override
    public List<Cart> findCartByUserID(String userID){
        return cartRepository.findByCartUsers_UserId(userID);
    }

    //  Find cart ID
    @Override
    public Cart findByID(String cartID) throws Exception {
        Cart cart = cartRepository.findByCartID(cartID);
        if(cart != null){
            return cart;
        }else{
            throw new Exception("Can't find cart with id: " + cartID);
        }
    }

    //  Create cart
    @Override
    public Cart save(String userID) throws Exception {
        Users user = userService.findById(userID);
        Cart cart = new Cart();
        cart.setCartUsers(user);
        return cartRepository.save(cart);
    }

    //  Change amount in product
    public void decreaseAmountProduct(String productID) throws Exception {
        Product product =  productService.findById(productID);
        product.getDetails().setProductAmount(product.getDetails().getProductAmount() - 1);
        productRepository.save(product);
    }

    public void increaseAmountProduct(String productID) throws Exception {
        Product product =  productService.findById(productID);
        product.getDetails().setProductAmount(product.getDetails().getProductAmount() + 1);
        productRepository.save(product);
    }

    //  Add item in cart
    @Override
    public Cart addProductInCart(String productID, String cartID) throws Exception {
        Cart cart = findByID(cartID);
        Product product = productService.findById(productID);
        cart.getCartProducts().add(product);
        cart.setPrice(cart.getPrice() + product.getProductCost());
        cart.setCartQuantity(cart.getCartQuantity() + 1);
        decreaseAmountProduct(productID);
        return cartRepository.save(cart);
    }

    //  Delete item in order
    @Override
    public Cart deleteProductInCart(String productID, String cartID) throws Exception {
        Cart cart = findByID(cartID);
        Product product = productService.findById(productID);
        cart.getCartProducts().remove(product);
        cart.setPrice(cart.getPrice() - product.getProductCost());
        cart.setCartQuantity(cart.getCartQuantity() - 1);
        increaseAmountProduct(productID);
        return cartRepository.save(cart);
    }

    //  Delete cart
    @Override
    public String deleteCartById(String id){
        cartRepository.deleteById(id);
        return "Remove success with cart ID: " + id;
    }
}
