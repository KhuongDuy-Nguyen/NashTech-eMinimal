package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.users.Users;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {
    //  Find account
    List<Users> findAll() throws FirebaseAuthException, ExecutionException, InterruptedException;

    Users findById(String uuid) throws Exception;

    //  Create account
    <S extends Users> String save(S entity) throws Exception;

    //  Delete account
    String deleteById(String uuid) throws Exception;

    //   Update account
    String updateUserById(Users newUsers) throws Exception;

}
