package com.eminimal.backend.dto;

import com.eminimal.backend.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto implements Serializable {
    private String productID;
    private String productName;
    private String productDesc;
    private String productImage;
    private float productCost;
    private int productSale;
    private List<Integer> productRating;
    private int productAmount;
    private Date dateCreate;
    private Date dateUpdate;
    private Date dateSale;
    private CategoryDto categories;
}