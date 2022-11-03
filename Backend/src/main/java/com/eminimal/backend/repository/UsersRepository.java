package com.eminimal.backend.repository;

import com.eminimal.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUserEmail(String email);

    Users findByUserId(String id);

    Users findByUserName(String username);

    Boolean existsByUserName(String username);
    Boolean existsByUserEmail(String email);

}