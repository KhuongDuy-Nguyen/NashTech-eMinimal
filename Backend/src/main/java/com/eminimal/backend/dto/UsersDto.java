package com.eminimal.backend.dto;

import com.eminimal.backend.models.Users;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Users} entity
 */
@Data
public class UsersDto implements Serializable {
    private final String userId;
    private final String userName;
    private final String userEmail;
}