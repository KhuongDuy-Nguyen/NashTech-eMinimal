package com.eminimal.backend.services.impl;

<<<<<<< Updated upstream
import com.eminimal.backend.models.UserDetails;
=======
import com.eminimal.backend.exceptions.ResourceFoundException;
>>>>>>> Stashed changes
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.UserDetailsRepository;
import com.eminimal.backend.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements com.eminimal.backend.services.interfaces.UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersRepository repository;

    @Autowired
    private UserDetailsRepository detailsRepository;

    int strength = 10; // work factor of bcrypt
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

//  Find account

    @Override
    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users findById(String id) throws Exception {
        Users users = repository.findByUserId(id);
        if(users != null){
            return users;
        }else{
            throw new Exception("Can not find user with id: " + id);
        }
    }

    @Override
    public Users findByEmail(String email) throws Exception {
        Users users = repository.findByUserEmail(email);
        if(users != null){
            return users;
        }else{
            throw new Exception("Can not find user with email: " + email);
        }
    }

    //  Create account
    @Override
    public Users save(Users entity) throws Exception {
        if(repository.existsByUserEmail(entity.getUserEmail())){
            throw new Exception("Email have been taken");
        } else if (repository.existsByUserName(entity.getUserName())) {
            throw new Exception("Username have been taken");
        }
        entity.setUserPassword(hashPass(entity.getUserPassword()));
        entity.setDetails(new UserDetails());
        return repository.save(entity);
    }

    public String hashPass(String pass){
        return bCryptPasswordEncoder.encode(pass);
    }


//  Delete account

    @Override
    public String deleteById(String uuid) throws Exception {
        findById(uuid);
        repository.deleteById(uuid);
        return "Remove user success with id: " + uuid;
    }

//   Update account

    @Override
    public Users updateUserById(Users newUsers) throws Exception {
        Users user = findById(newUsers.getUserId());
<<<<<<< Updated upstream

        UserDetails details = detailsRepository.findByUserDetailsID(newUsers.getDetails().getUserDetailsID());

         details = UserDetails.builder()
                .userDetailsID(user.getDetails().getUserDetailsID())
                .userImage(newUsers.getDetails().getUserImage())
                .userPhone(newUsers.getDetails().getUserPhone())
                .userAddress(newUsers.getDetails().getUserAddress())
                .userCountry(newUsers.getDetails().getUserCountry())
                .userRole(newUsers.getDetails().getUserRole())
                .userActive(newUsers.getDetails().isUserActive())
                .build();

        user = Users.builder()
                .userId(user.getUserId())
                .userName(newUsers.getUserName())
                .userEmail(newUsers.getUserEmail())
                .userPassword(hashPass(newUsers.getUserPassword()))
                .details(details)
                .build();
=======
        if(!bCryptPasswordEncoder.matches(newUsers.getUserPassword(), user.getUserPassword()))
            throw new Exception("Password not match");

        newUsers.setUserPassword(hashPass(newUsers.getUserPassword()));
        user = modelMapper.map(newUsers, Users.class);

        logger.error("User update: " + user);
>>>>>>> Stashed changes

        return repository.save(user);
    }

    @Override
    public UserDetails activeUserByUserEmail(String email) throws Exception {
        Users users = findByEmail(email);

        UserDetails details = detailsRepository.findByUserDetailsID(users.getDetails().getUserDetailsID());
        details.setUserActive(true);
        return detailsRepository.save(details);
    }

    @Override
    public UserDetails changeRoleByUserEmail(String email, String role) throws Exception {
        Users users = findByEmail(email);

        UserDetails details = detailsRepository.findByUserDetailsID(users.getDetails().getUserDetailsID());
        details.setUserRole(role);
        return detailsRepository.save(details);
    }

    @Override
    public Users changePasswordByUserId(String userID, String oldPass, String newPass) throws Exception {
        Users users = findById(userID);
        if(!bCryptPasswordEncoder.matches(oldPass, users.getUserPassword())){
            throw new Exception("Password not match!");
        }
        users.setUserPassword(hashPass(newPass));
        return repository.save(users);
    }
}
