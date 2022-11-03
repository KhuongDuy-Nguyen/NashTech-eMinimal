package com.eminimal.backend.repository;

import com.eminimal.backend.models.UsersToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTokenRepository extends JpaRepository<UsersToken, String> {
    UsersToken findByUserId(String id);
}