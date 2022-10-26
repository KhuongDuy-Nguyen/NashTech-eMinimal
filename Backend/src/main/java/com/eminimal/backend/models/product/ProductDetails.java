package com.eminimal.backend.models.product;

import com.eminimal.backend.models.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails{

    @Id
    private String productDetailID = UUID.randomUUID().toString();

    private int productAmount ;
    private Date dateCreate = new Date();
    private Date dateUpdate;
    private int productSale ;
    private Date dateSale;

    @ManyToOne
    private Category categories;

    public ProductDetails(int productAmount, Category categories) {
        this.productAmount = productAmount;
        this.categories = categories;
    }

    public ProductDetails(int productAmount, int productSale, Date dateSale, Category categories) {
        this.productAmount = productAmount;
        this.productSale = productSale;
        this.dateSale = dateSale;
        this.categories = categories;
    }

}
