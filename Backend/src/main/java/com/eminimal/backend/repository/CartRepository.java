package com.eminimal.backend.repository;

import com.eminimal.backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCartID(String id);
    List<Cart> findByCartUsers_UserId(String userID);

    Cart findCartByCartID(String cartID);

    Cart findByCartUsers_UserIdAndCartStatusIsFalse(String userID);

}