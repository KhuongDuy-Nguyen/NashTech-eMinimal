package com.eminimal.backend.repository;

import com.eminimal.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Users findByUserEmail(String email);

    Users findByUserId(UUID id);
}