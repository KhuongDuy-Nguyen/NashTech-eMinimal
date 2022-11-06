package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
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
    @ElementCollection
    private List<String> productImage = new ArrayList<>();

    private float  productCost;

    @ElementCollection
    private List<Integer> productRating = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetails details;

    public Product(String productName, String productDesc, List<String> productImage, float productCost,  ProductDetails details) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.details = details;
    }

    public Product(String productName, String productDesc, float productCost, ProductDetails details) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCost = productCost;
        this.details = details;
    }
}