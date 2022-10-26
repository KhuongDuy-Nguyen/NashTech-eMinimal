package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.services.impl.users.UserServiceImpl;
import com.eminimal.backend.services.interfaces.CartService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class CartServiceImpl implements CartService {

    private static final String COLLECTION_NAME = "cart";
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final Firestore dbFirestore = FirestoreClient.getFirestore();
    private final List<Cart> cartList = new ArrayList<>();

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserServiceImpl userService;


//    Find all cart
    @Override
    public List<Cart> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Cart cart = document.toObject(Cart.class);
            cartList.add(cart);
        }
        return cartList;
    }

    @Override
    public Cart findById(String cartID) throws ExecutionException, InterruptedException {
//        return cartRepository.findByCartUsers_UserId(userID);
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(cartID).get();
        DocumentSnapshot document = future.get();
        if(document.exists()){
            return document.toObject(Cart.class);
        }else{
            throw new ExecutionException("Can't find cart with id: " + cartID, null);
        }
    }

//  Add item in order
    @Override
    public String save(String usersID, String productID) throws Exception {
        Users user = userService.findById(usersID);
        Product product = productService.findById(productID);
        Cart cart = new Cart();
        cart.getCartProducts().add(product);
        cart.setCartQuantity(cart.getCartQuantity() + 1);
        cart.setPrice(cart.getPrice() + product.getProductCost());
        cart.setCartUsers(user);

        dbFirestore.collection(COLLECTION_NAME).document(cart.getCartID()).set(cart);
        decreaseAmountProduct(productID);
        return "Create success";
    }

//    Change amount in product
    public void decreaseAmountProduct(String productID) throws ExecutionException, InterruptedException {
        Product product =  productService.findById(productID);
        product.setProductAmount(product.getProductAmount() - 1);
        dbFirestore.collection(COLLECTION_NAME).document(product.getProductID()).set(product);
    }

    public void increaseAmountProduct(String productID) throws ExecutionException, InterruptedException {
        Product product = productService.findById(productID);
        product.setProductAmount(product.getProductAmount() + 1);
        dbFirestore.collection(COLLECTION_NAME).document(product.getProductID()).set(product);
    }

//    Update cart
    @Override
    public String updateCart(Cart newCart) throws ExecutionException, InterruptedException {
        findById(newCart.getCartID());
        dbFirestore.collection(COLLECTION_NAME).document(newCart.getCartID()).set(newCart);
        return "Update success";
    }


    //  Delete item in order
    @Override
    public String deleteProductById(String cartID, String productID) throws ExecutionException, InterruptedException {
            Cart cart = findById(cartID);
            Product product = productService.findById(productID);
            cart.getCartProducts().remove(product);
            increaseAmountProduct(productID);
            dbFirestore.collection(COLLECTION_NAME).document(cartID).set(cart);
            return "Remove item in order success";
    }

    @Override
    public String deleteCartById(String id){
        dbFirestore.collection(COLLECTION_NAME).document(id).delete();
        return "Remove success with cart ID: " + id;
    }
}
