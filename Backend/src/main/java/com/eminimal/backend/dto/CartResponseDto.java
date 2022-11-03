package com.eminimal.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eminimal.backend.models.Cart} entity
 */
@Data
public class CartResponseDto implements Serializable {
    private final String cartID;
    private final int cartQuantity;
    private final float price;
    private final boolean cartStatus;
}