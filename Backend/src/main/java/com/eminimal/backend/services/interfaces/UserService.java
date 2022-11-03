package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.users.UserDetails;
import com.eminimal.backend.models.users.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();

    Users findById(String id) throws Exception;

    Users findByEmail(String email) throws Exception;

    //  Create account
    Users save(Users entity) throws Exception;

    String deleteById(String uuid) throws Exception;

    Users updateUserById(Users newUsers) throws Exception;

    UserDetails activeUserByUserEmail(String email) throws Exception;

    UserDetails changeRoleByUserEmail(String email, String role) throws Exception;
}
