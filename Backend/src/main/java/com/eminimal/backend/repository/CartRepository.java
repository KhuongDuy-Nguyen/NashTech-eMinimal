package com.eminimal.backend.repository;

import com.eminimal.backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCartID(String id);
    List<Cart> findByCartUsers_UserId(String userID);

}