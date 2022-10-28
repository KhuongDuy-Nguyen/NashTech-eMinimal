package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.users.CustomUserDetails;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(String id){
        Users user = usersRepository.findByUserId(id);
        if (user == null) {
            throw new UsernameNotFoundException(id);
        }
        return new CustomUserDetails(user);
    }

}
