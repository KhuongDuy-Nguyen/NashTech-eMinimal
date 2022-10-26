package com.eminimal.backend.dto;

import com.eminimal.backend.models.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link Users} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UsersDto implements Serializable {
    private String userId;
    private String userName;
    private String userEmail;
    private String userRole;
    private String userImage;
    private String userPhone;
    private String userAddress;
    private String userCountry;

}