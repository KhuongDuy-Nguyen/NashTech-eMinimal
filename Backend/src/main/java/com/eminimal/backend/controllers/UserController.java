package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.UsersDto;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.services.impl.users.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
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
    List<UsersDto> findAll(){
        return service.findAll().stream().map(users -> modelMapper.map(users, UsersDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    ResponseEntity<UsersDto> findUserByID(@PathVariable String id ) throws Exception {
        Users users = service.findById(id);
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping("/email={email}")
    ResponseEntity<UsersDto> findUserByEmail(@PathVariable String email) throws Exception {
        Users users = service.findByEmail(email);
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);
        return ResponseEntity.ok().body(usersDto);
    }

    //    Create new account
    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody Users users) throws Exception {
        service.save(users);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    //    Update account
    @PutMapping("/update")
    ResponseEntity<?> updateUser(@RequestParam String id, @RequestBody Users users) throws Exception {
        service.updateUserById(id, users);
        return new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

    //    Remove account
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUserById(@RequestParam String id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

//   Active account
    @PutMapping("/active")
    ResponseEntity<?> activeUserById(@RequestParam String email) throws Exception {
        service.activeUser(email);
        return new ResponseEntity<>("Active successfully", HttpStatus.OK);
    }
}
