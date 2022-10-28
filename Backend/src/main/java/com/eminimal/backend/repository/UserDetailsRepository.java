package com.eminimal.backend.repository;

import com.eminimal.backend.models.users.UserDetails;
import com.eminimal.backend.models.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    UserDetails findByUserDetailsID(String ID);

}