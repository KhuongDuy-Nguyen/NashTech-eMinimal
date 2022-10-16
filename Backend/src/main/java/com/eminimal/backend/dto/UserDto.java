package com.eminimal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.eminimal.backend.models.User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto implements Serializable {
    private UUID userID = UUID.randomUUID();
    private String userName;
    private String userPassword;
    private String userImage;
    private String userRole;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userCountry;
    private boolean userActive;
    private OrderDto orders;
}