package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.UsersDto;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.services.impl.users.UserServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    //  Get account
    @GetMapping("/all")
    List<UsersDto> findAll() throws FirebaseAuthException, ExecutionException, InterruptedException {
        return service.findAll().stream().map(users -> modelMapper.map(users, UsersDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    ResponseEntity<UsersDto> findUserByID(@PathVariable String id ) throws Exception {
        Users users = service.findById(id);
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping("/email={email}")
    ResponseEntity<?> findUserByEmail(@PathVariable String email) throws Exception {
//        Users users = service.findByEmail(email);
//        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
//        System.out.println("Successfully fetched user data: " + userRecord.getEmail());
        return ResponseEntity.ok().body(userRecord.getProviderData());
    }

    //    Create new account
    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody Users users) throws Exception {
        return new ResponseEntity<>(service.save(users), HttpStatus.CREATED);
    }

    //    Update account
    @PutMapping("/update")
    ResponseEntity<?> updateUser(@RequestParam String id, @RequestBody Users users) throws Exception {
        return new ResponseEntity<>(service.updateUserById(users), HttpStatus.OK);
    }

    //    Remove account
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUserById(@RequestParam String id) throws Exception {
        ;
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

}
