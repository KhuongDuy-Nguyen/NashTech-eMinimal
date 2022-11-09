package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Mock
    UsersRepository usersRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @InjectMocks
    UserServiceImpl userServiceImpl;

    Users initUsers;
    Users expectedUsers;

    private final List<Users> mockUsers = new ArrayList<>();


    @BeforeEach
    void setup(){

        initUsers = mock(Users.class);
        expectedUsers = mock(Users.class);

        for(int i = 0; i< 5; i++){
            mockUsers.add(new Users( "admin " + i, "123 ",  "admin@gmail.com"));
        }

        initUsers = new Users(
                "1","name", "123","email@gmail.com",
                "123","address","country", true, "ADMIN" );


//        when(usersRepository.findByUserId("1")).thenReturn(initUsers);
//        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);
    }

//    Valid Data
    @Test
    void findAll_ShouldReturnList_WhenGetAll() {
        when(usersRepository.findAll()).thenReturn(mockUsers);

        List<Users> actualUsers = userServiceImpl.findAll();

        assertThat(actualUsers.size()).isEqualTo(mockUsers.size());
    }

    @Test
    void findById_ShouldReturnUsers_WhenFoundById() throws Exception {
        when(usersRepository.findByUserId("1")).thenReturn(initUsers);

        Users result = userServiceImpl.findById("1");

        assertThat(result).isEqualTo(initUsers);
    }

    @Test
    void findByEmail_ShouldReturnUsers_WhenFoundByEmail() throws Exception {

        when(usersRepository.findByUserEmail("admin@gmail.com")).thenReturn(initUsers);

        Users result = userServiceImpl.findByEmail("admin@gmail.com");

        assertThat(result).isEqualTo(initUsers);
    }

    @Test
    void save_ShouldReturnUsers_WhenDataValid() throws Exception {
        Users newUser = Users.builder().userId("1").userName("admin").userPassword("123").userEmail("admin@gmail.com").build();

        when(usersRepository.save(any(Users.class))).thenReturn(initUsers);

        Users result = userServiceImpl.save(newUser);
        assertEquals(result, initUsers);
    }

    @Test
    void deleteById_ShouldReturnMessageAndCanNotFound_WhenFoundId() throws Exception {
        when(usersRepository.findByUserId("1")).thenReturn(new Users());

        String message = userServiceImpl.deleteById("1");
        assertEquals("Remove user success with id: 1", message);

        verify(usersRepository, times(1)).deleteById("1");
    }

    @Test
    void updateUserById_ShouldReturnUsers_WhenDataValid() throws Exception {
        when(usersRepository.findByUserId("1")).thenReturn(initUsers);

        Users newUsers = new Users(
                "1","name", "123","email@gmail.com",
                "123","address","country", true, "ADMIN" );

        when(bCryptPasswordEncoder.matches(anyString(),anyString())).thenReturn(true);

        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users result = userServiceImpl.updateUserById(newUsers);
        assertEquals(result, expectedUsers);
    }

    @Test
    void activeUserByUserEmail() throws Exception {
        when(usersRepository.findByUserEmail("email@gmail.com")).thenReturn(initUsers);
        initUsers.setUserActive(true);
        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users newUsers = new Users(
                "1","name", "123","email@gmail.com",
                "123","address","country", false, "ADMIN" );

        Users result = userServiceImpl.activeUserByUserEmail(newUsers.getUserEmail());
        assertEquals(result.isUserActive(), expectedUsers.isUserActive());
//        verify(expectedUsers).setUserActive(true);
    }

    @Test
    void changeRoleByUserEmail() {

    }
}