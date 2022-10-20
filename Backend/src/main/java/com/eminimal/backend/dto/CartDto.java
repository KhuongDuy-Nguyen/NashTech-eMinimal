package com.eminimal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link com.eminimal.backend.models.Cart} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartDto implements Serializable {
    private UUID cartID = UUID.randomUUID();
    private int cartQuantity;
    private float price;
    private boolean cartStatus;
    private List<ProductDto> cartProducts = new ArrayList<>();
    private UsersDto cartUsers;
}