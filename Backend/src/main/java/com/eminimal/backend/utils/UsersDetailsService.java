package com.eminimal.backend.utils;

import com.eminimal.backend.models.CustomUserDetails;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

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
