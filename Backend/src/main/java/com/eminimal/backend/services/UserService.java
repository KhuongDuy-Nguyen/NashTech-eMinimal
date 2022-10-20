package com.eminimal.backend.services;

import com.eminimal.backend.models.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    //  Find account
    List<Users> findAll();

    Users findById(UUID uuid);

    Users findByEmail(String email);

    //  Create account
    <S extends Users> S save(S entity);

    //  Delete account
    void deleteById(UUID uuid);

    //   Update account
    void updateUserById(UUID id, Users newUsers);

    void activeUser(String email);

    String hashPass(String password);
}
