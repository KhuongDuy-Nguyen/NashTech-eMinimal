package com.eminimal.backend.repository;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCartID(String id);
    Cart findByCartUsers_UserId(String userID);

}