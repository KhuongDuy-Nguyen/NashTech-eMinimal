package com.eminimal.backend.dto;

import com.eminimal.backend.models.UserDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserDetails} entity
 */
@Data
public class UsersDetailsResponseDto implements Serializable {
    private final String userDetailsID;
    private final String userImage;
    private final String userPhone;
    private final String userAddress;
    private final String userCountry;
    private final boolean userActive;
    private final String userRole;
}