package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.UsersDto;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.services.impl.users.UserAuth;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   @Autowired
   private UserAuth auth;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Users users) throws Exception {
//        Users users = modelMapper.map(usersDto, Users.class);
        String token = auth.login(users);
        return new ResponseEntity<>("Token: " + auth.login(users), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users) throws Exception {
//        Users users = modelMapper.map(usersDto, Users.class);
        return ResponseEntity.ok().body(auth.register(users));
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) throws Exception {
//        Users users = modelMapper.map(loginDto, Users.class);
//        service.authenticateUser(users);
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        logger.info("getPrincipal: " + auth.getPrincipal());
//        UsersToken token = new UsersToken();
////        token.setToken(jwtUtil.generateToken(UserDetails));
//        token.setTokenExpDate(jwtUtil.generateExpirationDate());
//        usersTokenService.createToken(token);
//        return ResponseEntity.ok(token.getToken());
//
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody Users users) throws Exception {
////        Users users = modelMapper.map(signUpDto, Users.class);
////        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
////                .setEmail(users.getUserEmail())
////                .setPassword(users.getUserPassword())
////                .setPhoneNumber(users.getUserPhone())
////                .setDisplayName(users.getUserName())
////                .setPhotoUrl(users.getUserImage())
////                .setEmailVerified(false);
////
////        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//        return new ResponseEntity<>(service.register(users), HttpStatus.OK);
////        return new ResponseEntity<>("Successfully created new user: " + userRecord.getUid(), HttpStatus.OK);
//    }
//
//    @GetMapping("/test")
//    public String getUser(@RequestParam String email) throws FirebaseAuthException {
//        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
//// See the UserRecord reference doc for the contents of userRecord.
//        return userRecord.getUid();
//    }
}
