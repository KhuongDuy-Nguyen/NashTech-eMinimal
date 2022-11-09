package com.eminimal.backend.services.impl;

import com.eminimal.backend.exceptions.NotFoundException;
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
                "1","name", bCryptPasswordEncoder.encode("123"),"email@gmail.com",
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
    void findById_ShouldThrowNotFoundException_WhenNotFoundById() throws Exception {
        NotFoundException actualException = assertThrows(NotFoundException.class, () -> userServiceImpl.findById("1"));
        assertEquals("Can not find user with id: 1", actualException.getMessage());
    }

    @Test
    void findById_ShouldThrowNullPointException_WhenIdIsNull() throws Exception {
        NullPointerException actualException = assertThrows(NullPointerException.class, () -> userServiceImpl.findById(""));
        assertEquals("ID can not be null or blank", actualException.getMessage());
    }


    @Test
    void findByEmail_ShouldReturnUsers_WhenFoundByEmail() throws Exception {

        when(usersRepository.findByUserEmail("admin@gmail.com")).thenReturn(initUsers);

        Users result = userServiceImpl.findByEmail("admin@gmail.com");

        assertThat(result).isEqualTo(initUsers);
    }

    @Test
    void findByEmail_ShouldThrowNotFoundException_WhenNotFoundByEmail() throws Exception {
        NotFoundException actualException = assertThrows(NotFoundException.class, () -> userServiceImpl.findByEmail("ad@gmail.com"));
        assertEquals("Can not find user with email: ad@gmail.com", actualException.getMessage());
    }

    @Test
    void findByEmail_ShouldThrowNullPointException_WhenEmailIsNull() throws Exception {
        NullPointerException actualException = assertThrows(NullPointerException.class, () -> userServiceImpl.findByEmail(""));
        assertEquals("Email can not be null or blank", actualException.getMessage());
    }


    @Test
    void save_ShouldReturnUsers_WhenDataValid() throws Exception {
        Users newUser = Users.builder().userId("1").userName("admin").userPassword("123").userEmail("admin@gmail.com").build();

        when(usersRepository.save(any(Users.class))).thenReturn(initUsers);

        Users result = userServiceImpl.save(newUser);
        assertEquals(result, initUsers);
    }

    @Test
    void save_ShouldThrowException_WhenEmailHaveTaken(){
        when(usersRepository.existsByUserEmail("admin@gmail.com")).thenReturn(true);

        Exception actualException = assertThrows(Exception.class, () ->
                userServiceImpl.save(new Users("admin", "123", "admin@gmail.com")));
        assertEquals("Email have been taken", actualException.getMessage());
    }

    @Test
    void save_ShouldThrowException_WhenUsernameHaveTaken(){
        when(usersRepository.existsByUserName("admin")).thenReturn(true);

        Exception actualException = assertThrows(Exception.class, () ->
                userServiceImpl.save(new Users("admin", "123", "admin@gmail.com")));
        assertEquals("Username have been taken", actualException.getMessage());
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

        when(bCryptPasswordEncoder.matches("123",initUsers.getUserPassword())).thenReturn(true);

        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users result = userServiceImpl.updateUserById(newUsers);
        assertEquals(result, expectedUsers);
    }

    @Test
    void updateUserById_ShouldThrowException_WhenPasswordNotMatch(){
        Users newUsers = new Users(
                "1","name", "12345","email@gmail.com",
                "123","address","country", true, "ADMIN" );

        when(usersRepository.findByUserId("1")).thenReturn(initUsers);
        when(bCryptPasswordEncoder.matches("12345",initUsers.getUserPassword())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> userServiceImpl.updateUserById(newUsers));
        assertEquals("Password invalid", exception.getMessage());
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
    void changeRoleByUserEmail_ShouldReturnUsers_WhenChangeRoleToAdmin() throws Exception {
        when(usersRepository.findByUserEmail("ad@gmail.com")).thenReturn(initUsers);
        initUsers.setUserRole("ADMIN");
        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users result = userServiceImpl.changeRoleByUserEmail("ad@gmail.com", "ADMIN");
        assertEquals(result, expectedUsers);
    }

    @Test
    void changePasswordByUserId_ShouldReturnUsers_WhenChangePassword() throws Exception {
        when(usersRepository.findByUserId("1")).thenReturn(initUsers);

        when(bCryptPasswordEncoder.matches("123",initUsers.getUserPassword())).thenReturn(true);

        initUsers.setUserPassword(bCryptPasswordEncoder.encode("123"));
        when(usersRepository.save(initUsers)).thenReturn(expectedUsers);

        Users result = userServiceImpl.changePasswordByUserId("1", "123", "123123");

        assertEquals(result, expectedUsers);
        verify(bCryptPasswordEncoder).matches("123",initUsers.getUserPassword());
    }

    @Test
    void changePasswordByUserId_ShouldThrowException_WhenPasswordNotMatch() throws Exception {
        when(usersRepository.findByUserId("1")).thenReturn(initUsers);

        when(bCryptPasswordEncoder.matches("12345",initUsers.getUserPassword())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> userServiceImpl.changePasswordByUserId("1", "12345", "123456"));
        assertEquals("Password not match!", exception.getMessage());

    }
}