package com.eminimal.backend.dto;

import com.eminimal.backend.models.UsersToken;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link UsersToken} entity
 */
@Data
public class UsersTokenResponseDto implements Serializable {
    private final String tokenID;
    private final String userId;
    private final String token;
    private final Date createdDate;
    private final Date expiryDate;
}