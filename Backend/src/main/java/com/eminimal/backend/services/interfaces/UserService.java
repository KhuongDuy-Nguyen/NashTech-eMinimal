package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.users.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    //  Find account
    List<Users> findAll();

    Users findById(String uuid) throws Exception;

    Users findByEmail(String email) throws Exception;

    Users findByUsername(String username) throws Exception;

    //  Create account
    <S extends Users> S save(S entity) throws Exception;

    //  Delete account
    void deleteById(String uuid) throws Exception;

    //   Update account
    void updateUserById(String id, Users newUsers) throws Exception;

    void activeUser(String email) throws Exception;

}
