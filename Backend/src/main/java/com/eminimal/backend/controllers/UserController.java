package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.ProductDto;
import com.eminimal.backend.dto.UsersDto;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/account")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    //  Get account
    @GetMapping("/all")
    List<UsersDto> findAll(){
        return service.findAll().stream().map(users -> modelMapper.map(users, UsersDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    ResponseEntity<UsersDto> findUserByID(@PathVariable UUID id ){
        Users users = service.findById(id);
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping("/email={email}")
    ResponseEntity<UsersDto> findUserByEmail(@PathVariable String email){
        Users users = service.findByEmail(email);
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        return ResponseEntity.ok().body(usersDto);
    }

    //    Create new account
    @PostMapping("/create")
    ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto){
        Users usersRequest = modelMapper.map(usersDto, Users.class);
        service.save(usersRequest);

        UsersDto usersResponse = modelMapper.map(usersRequest, UsersDto.class);
        return new ResponseEntity<>(usersResponse, HttpStatus.CREATED);
    }

    //    Update account
    @PutMapping("/update")
    ResponseEntity<UsersDto> updateUser(@RequestParam UUID id, @RequestBody UsersDto usersDto){
        Users usersRequest = modelMapper.map(usersDto, Users.class);
        service.updateUserById(id, usersRequest);

        UsersDto usersResponse = modelMapper.map(usersRequest, UsersDto.class);
        return ResponseEntity.ok().body(usersResponse);
    }

    //    Remove account
    @DeleteMapping("/delete")
    ResponseEntity<io.swagger.v3.oas.models.responses.ApiResponse> deleteUserById(@RequestParam UUID id){
        service.deleteById(id);
        io.swagger.v3.oas.models.responses.ApiResponse apiResponse = new io.swagger.v3.oas.models.responses.ApiResponse();
        apiResponse.setDescription("Remove successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//   Active account
    @PutMapping("/active")
    ResponseEntity<io.swagger.v3.oas.models.responses.ApiResponse> activeUserById(@RequestParam String email){
        io.swagger.v3.oas.models.responses.ApiResponse apiResponse = new io.swagger.v3.oas.models.responses.ApiResponse();
        service.activeUser(email);
        apiResponse.setDescription("Active successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
