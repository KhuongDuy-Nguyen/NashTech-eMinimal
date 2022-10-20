package com.eminimal.backend.dto;

import com.eminimal.backend.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link Cart} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartDto implements Serializable {
    private UUID orderID;
    private int quantity;
    private float price;
    private boolean status;
    private List<ProductDto> products;
    private UsersDto users;
}