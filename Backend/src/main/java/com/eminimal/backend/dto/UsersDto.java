package com.eminimal.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eminimal.backend.models.users.Users} entity
 */
@Data
public class UsersDto implements Serializable {
    private final String userId;
    private final String userName;
    private final String userEmail;
}