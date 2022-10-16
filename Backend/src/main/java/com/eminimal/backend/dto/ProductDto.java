package com.eminimal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link com.eminimal.backend.models.Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto implements Serializable {
    private UUID productID = UUID.randomUUID();
    private String productName;
    private String productDesc;
    private String productImage;
    private float productCost;
    private int productSale;
    private int productRating;
    private int productAmount;
    private Date dateCreate = new Date();
    private Date dateUpdate;
    private Date dateSale;
    private CategoryDto categories;
}