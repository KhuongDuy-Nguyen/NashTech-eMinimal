package com.eminimal.backend.repository;

import com.eminimal.backend.models.users.UsersToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTokenRepository extends JpaRepository<UsersToken, String> {
    UsersToken findByUserId(String id);
}