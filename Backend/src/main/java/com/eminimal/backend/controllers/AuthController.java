package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.ErrorResponse;
import com.eminimal.backend.exceptions.NotFoundException;
import com.eminimal.backend.exceptions.ResourceFoundException;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.models.UsersToken;
import com.eminimal.backend.repository.UsersTokenRepository;
import com.eminimal.backend.utils.AuthService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   @Autowired
   private AuthService auth;

   @Autowired
   private UsersTokenRepository tokenRepository;


    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/login")
    public ResponseEntity<UsersToken> authenticateUser(@Valid @RequestBody Users users) throws Exception {
//        Users users = modelMapper.map(usersDto, Users.class);
        return new ResponseEntity<>(auth.login(users), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users) throws Exception {
//        Users users = modelMapper.map(usersDto, Users.class);
//        Users users = modelMapper.map(registerDto, Users.class);
        return ResponseEntity.ok().body(auth.register(users));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String email) throws Exception {
        return ResponseEntity.ok().body(auth.logout(email));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleCategoryNotFoundException(){
        ErrorResponse errorResponse = new ErrorResponse("01", "Invalid username and password");
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResourceFoundException.class)
    ResponseEntity<ErrorResponse> resourceFoundException(){
        ErrorResponse errorResponse = new ErrorResponse("03", "Something was wrong. Try login again");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
