package com.eminimal.backend.services.impl.users;

import com.eminimal.backend.jwt.JwtTokenProvider;
import com.eminimal.backend.models.users.CustomUserDetails;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.repository.UsersRepository;
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
public class UserAuth {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(UserAuth.class);

    public Users register(Users users){
        users.setUserPassword(passwordEncoder.encode(users.getUserPassword()));
        return userRepository.save(users);
    }

    public String login(Users users) throws Exception {
        // Xác thực từ username và password.
        logger.info("users: " + users);

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
            throw new Exception("Invalid username and password");
        }
    }

}
