package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements com.eminimal.backend.services.UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

//  Find account
    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users findById(UUID uuid) {
        return repository.findByUserId(uuid);
    }

    @Override
    public Users findByEmail(String email){
        return repository.findByUserEmail(email);
    }

//  Create account
    @Override
    public <S extends Users> S save(S entity) {
        entity.setUserPassword(hashPass(entity.getUserPassword()));
        return repository.save(entity);
    }

    @Override
    public String hashPass(String pass){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(pass);
    }

//  Delete account
    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

//   Update account
    @Override
    public void updateUserById(UUID id, Users newUsers){
        Users users = repository.findByUserId(id);
        users.setUserPassword(newUsers.getUserPassword());
        users.setUserImage(newUsers.getUserImage());
        users.setUserRole(newUsers.getUserRole());
        users.setUserPhone(newUsers.getUserPhone());
        users.setUserEmail(newUsers.getUserEmail());
        users.setUserAddress(newUsers.getUserAddress());
        users.setUserCountry(newUsers.getUserCountry());
        repository.save(users);
    }

    @Override
    public void activeUser(String email){
        Users users = repository.findByUserEmail(email);
        users.setUserActive(true);
        repository.save(users);
    }
}
