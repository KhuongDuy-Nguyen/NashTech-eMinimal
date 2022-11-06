package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Users;
import com.eminimal.backend.models.UsersToken;
import com.eminimal.backend.services.interfaces.UserService;
import com.eminimal.backend.utils.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@WithMockUser(roles = "GUEST")
class AuthServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);

    @Mock
    UserService userService;


    @Mock
    AuthenticationManager manager;
//
//    @Mock
//    JwtTokenProvider tokenProvider;

    @InjectMocks
    AuthService authService;

//    @Mock
//    UsersRepository usersRepository;

    Users initUser;
    Users expectedUser;

    @BeforeEach
    void setUp() throws Exception {

        initUser = mock(Users.class);
        expectedUser = mock(Users.class);

//        userAuthServiceImpl = new UserAuthServiceImpl(userService, manager, tokenProvider);

        initUser = Users.builder().userId("1").userName("admin").userEmail("admin@gmail.com").userPassword("123").build();
        when(authService.register(initUser)).thenReturn(initUser);
        logger.info("Users: " + initUser);
    }


    @Test
    void login_ShouldReturnUsers_WhenDataValid() throws Exception {
        UsersToken token = mock(UsersToken.class);
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);

        when(authService.login(initUser)).thenReturn(token);

        UsersToken result = authService.login(initUser);

        assertEquals(result, token);

    }

    @Test
    void logout() {
    }
}