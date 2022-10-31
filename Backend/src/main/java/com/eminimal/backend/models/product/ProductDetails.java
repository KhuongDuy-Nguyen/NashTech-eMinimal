package com.eminimal.backend.models.product;

import com.eminimal.backend.models.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date dateCreate = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date dateUpdate;


    private int productSale ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "GMT+7")
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
