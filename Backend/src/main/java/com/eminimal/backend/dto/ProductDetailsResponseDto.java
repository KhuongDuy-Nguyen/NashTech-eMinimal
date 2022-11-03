package com.eminimal.backend.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.eminimal.backend.models.product.ProductDetails} entity
 */
@Data
public class ProductDetailsResponseDto implements Serializable {
    private final String productDetailID;
    private final int productAmount;
    private final Date dateCreate;
    private final Date dateUpdate;
    private final int productSale;
    private final Date dateSale;
}