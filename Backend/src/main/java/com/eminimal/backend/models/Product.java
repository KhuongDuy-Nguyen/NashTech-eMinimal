package com.eminimal.backend.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "product_id"))
public class Product {
    @Id
    @Column(name = "product_id")
    private String productID = UUID.randomUUID().toString();

    private String productName ;
    private String productDesc ;
    private String productImage ;
    private float  productCost;

    private int productSale ;

    @ElementCollection
    private List<Integer> productRating = new ArrayList<>();

    private int productAmount ;

    private Date dateCreate = new Date();

    private Date dateUpdate;

    private Date dateSale;

    @ManyToOne
    private Category categories;

    public Product(String productName, String productDesc, String productImage, float productCost, int productAmount, Category categories) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productAmount = productAmount;
        this.categories = categories;
    }

}