package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.models.users.UsersToken;

public interface UserAuthService {
    Users register(Users users) throws Exception;

    UsersToken login(Users users) throws Exception;

    String logout(String email) throws Exception;
}
