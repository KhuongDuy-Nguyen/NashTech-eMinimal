package com.eminimal.backend.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.eminimal.backend.models.product.Product} entity
 */
@Data
public class ProductResponseDto implements Serializable {
    private final String productID;
    private final String productName;
    private final String productDesc;
    private final List<String> productImage;
    private final float productCost;
    private final List<Integer> productRating;
}