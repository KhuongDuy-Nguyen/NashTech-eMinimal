package com.eminimal.backend.controllers;

import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.services.impl.CartServiceImpl;
import com.eminimal.backend.services.impl.UserServiceImpl;
import com.eminimal.backend.services.interfaces.CartService;
import com.eminimal.backend.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private CartService cartService;

    //  Get account
    @GetMapping("/all")
    List<Users> findAll(){
        return service.findAll();
    }

    @GetMapping("")
    ResponseEntity<?> findUserByID(@RequestParam String id ) throws Exception {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/email={email}")
    ResponseEntity<?> findUserByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok().body(service.findByEmail(email));
    }

    //    Create new account
    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody Users users) throws Exception {
        Users newUser = service.save(users);
        cartService.save(newUser.getUserId());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //    Update account
    @PutMapping("/update")
    ResponseEntity<?> updateUser(@RequestBody Users newUsers) throws Exception {
        return new ResponseEntity<>(service.updateUserById(newUsers), HttpStatus.OK);
    }

    @PutMapping("/active")
    ResponseEntity<?> activeUser(@RequestParam String email) throws Exception {
        return new ResponseEntity<>(service.activeUserByUserEmail(email), HttpStatus.OK);
    }

    @PutMapping("/changeRole")
    ResponseEntity<?> changeRole(@RequestParam String email, @RequestParam String role) throws Exception {
        return new ResponseEntity<>(service.changeRoleByUserEmail(email, role), HttpStatus.OK);
    }

    //    Remove account
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUserById(@RequestParam String id) throws Exception {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

}
