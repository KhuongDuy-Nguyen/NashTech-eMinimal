package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.UserDetails;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.UserDetailsRepository;
import com.eminimal.backend.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    UserDetailsRepository detailsRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    Users initUsers;
    Users expectedUsers;

    UserDetails userDetails;

    private final List<Users> mockUsers = new ArrayList<>();

    @BeforeEach
    void setup(){
//        initUsers = mock(Users.class);
//        expectedUsers = mock(Users.class);
//        userDetails = mock(UserDetails.class);

        for(int i = 0; i< 5; i++){
            mockUsers.add(new Users( Integer.toString(i),"admin " + i, "123 ", "admin@gmail.com", new UserDetails()));
        }

//        when(usersRepository.save(initUsers)).thenReturn(initUsers);
//        when(usersRepository.findByUserId("1")).thenReturn(initUsers);
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
        initUsers = Users.builder().userId("1").userName("admin").userPassword("123").userEmail("admin@gmail.com").details(new UserDetails()).build();
        when(usersRepository.save(initUsers)).thenReturn(initUsers);
        Users result = userServiceImpl.save(initUsers);
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
//        expectedUsers = mock(Users.class);
        initUsers = mock(Users.class);
        expectedUsers = mock(Users.class);
        userDetails = mock(UserDetails.class);

        when(usersRepository.findByUserId("1")).thenReturn(initUsers);
        when(detailsRepository.findByUserDetailsID("1")).thenReturn(userDetails);
        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users result = userServiceImpl.updateUserById(new Users("1", "name", "123", "email@gmail.com", UserDetails.builder().userPhone("123123").build() ));

        logger.info("User: " + expectedUsers);
//        verify(initUsers).setUserName("name");
//        verify(initUsers).setUserEmail("email");
//        verify(initUsers).setUserPassword("123");
//        verify(initUsers).setDetails(userDetails);
//
//        assertEquals(result, expectedUsers);
    }

    @Test
    void activeUserByUserEmail() {
    }

    @Test
    void changeRoleByUserEmail() {
    }
}