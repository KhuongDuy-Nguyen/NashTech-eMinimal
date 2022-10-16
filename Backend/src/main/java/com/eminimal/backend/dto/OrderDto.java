package com.eminimal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

/**
 * A DTO for the {@link com.eminimal.backend.models.Order} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDto implements Serializable {
    private UUID orderID = UUID.randomUUID();
    private int amount;
    private float price;
    private Collection<ProductDto> products;
}