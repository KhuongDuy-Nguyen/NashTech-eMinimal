package com.eminimal.backend.repository;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByCartID(UUID id);
    Cart findByCartUsers_UserId(UUID userID);

}