package com.eminimal.backend.repository;

import com.eminimal.backend.models.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUserEmail(String email);

    Users findByUserId(String id);

    Users findByUserName(String username);

    Boolean existsByUserName(String username);
    Boolean existsByUserEmail(String email);

}