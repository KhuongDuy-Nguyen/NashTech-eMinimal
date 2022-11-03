package com.eminimal.backend.services.impl;

import com.eminimal.backend.jwt.JwtTokenProvider;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.models.users.UsersToken;
import com.eminimal.backend.repository.UsersRepository;
import com.eminimal.backend.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
class UserAuthServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImplTest.class);

    @Mock
    UserService userService;


    @Mock
    AuthenticationManager manager;
//
//    @Mock
//    JwtTokenProvider tokenProvider;

    @InjectMocks
    UserAuthServiceImpl userAuthServiceImpl;

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
        when(userAuthServiceImpl.register(initUser)).thenReturn(initUser);
        logger.info("Users: " + initUser);
    }


    @Test
    void login_ShouldReturnUsers_WhenDataValid() throws Exception {
        UsersToken token = mock(UsersToken.class);
        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);

        when(userAuthServiceImpl.login(initUser)).thenReturn(token);

        UsersToken result = userAuthServiceImpl.login(initUser);

        assertEquals(result, token);

    }

    @Test
    void logout() {
    }
}