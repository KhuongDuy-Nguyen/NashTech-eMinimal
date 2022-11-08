package com.eminimal.backend.services.impl;

import com.eminimal.backend.exceptions.NotFoundException;
import com.eminimal.backend.jwt.JwtTokenProvider;
import com.eminimal.backend.models.CustomUserDetails;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.models.UsersToken;
import com.eminimal.backend.repository.UsersTokenRepository;
import com.eminimal.backend.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements com.eminimal.backend.services.interfaces.UserAuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private  AuthenticationManager authenticationManager;


    @Autowired
    private  JwtTokenProvider tokenProvider;


    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    UsersTokenRepository tokenRepository;




    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Override
    public Users register(Users users) throws Exception {
        return userService.save(users);
    }

    @Override
    public UsersToken login(Users users) throws Exception {
        // Xác thực từ username và password.
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getUserName(),
                            users.getUserPassword()
                    )
            );
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            return tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());

        }catch (AuthenticationException e){
            throw new NotFoundException("Invalid username or password");
        }

    }

    @Override
    public String logout(String email) throws Exception {
        Users users = userService.findByEmail(email);
        UsersToken token = tokenRepository.findByUserId(users.getUserId());
        tokenRepository.deleteById(token.getTokenID());
        return "Logout success";
    }

}
