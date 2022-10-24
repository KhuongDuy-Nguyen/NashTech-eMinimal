package com.eminimal.backend.services.impl.users;

import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.repository.UsersRepository;
import com.eminimal.backend.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//  Find account
    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users findById(String uuid) throws Exception {
        if(repository.findByUserId(uuid) == null){
            throw new Exception("Can't find user with id: " + uuid);
        }
        return repository.findByUserId(uuid);

    }

    @Override
    public Users findByEmail(String email) throws Exception {
        if(repository.findByUserEmail(email) == null){
            throw new Exception("Can't find user with email: " + email);
        }
        return repository.findByUserEmail(email);

    }

    @Override
    public Users findByUsername(String username) throws Exception {
        if(repository.findByUserName(username) == null){
            throw new Exception("Can't find user with username: " + username);
        }
        return repository.findByUserName(username);
    }

    //  Create account
    @Override
    public <S extends Users> S save(S entity) throws Exception {
        if(repository.existsByUserName(entity.getUserName()) || repository.existsByUserEmail(entity.getUserEmail())){
            throw new Exception("This account have been taken!");
        }

        entity.setUserPassword(hashPass(entity.getUserPassword()));
        return repository.save(entity);
    }

    public String hashPass(String pass){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(pass);
    }


//  Delete account
    @Override
    public void deleteById(String uuid) throws Exception {
        findById(uuid);
        repository.deleteById(uuid);
    }

//   Update account
    @Override
    public void updateUserById(String id, Users newUsers) throws Exception {
        Users users = findById(id);

        if(newUsers.getUserPassword() != null)
            users.setUserPassword(newUsers.getUserPassword());

        if(newUsers.getUserImage() != null)
            users.setUserImage(newUsers.getUserImage());

        if(newUsers.getUserPhone() != null)
            users.setUserPhone(newUsers.getUserPhone());

        if(newUsers.getUserEmail() != null)
            users.setUserEmail(newUsers.getUserEmail());

        if(newUsers.getUserAddress() != null)
            users.setUserAddress(newUsers.getUserAddress());

        if(newUsers.getUserCountry() != null)
            users.setUserCountry(newUsers.getUserCountry());

        repository.save(users);
    }

    @Override
    public void activeUser(String email) throws Exception {
        Users users = findByEmail(email);
        users.setUserActive(true);
        repository.save(users);
    }

}
