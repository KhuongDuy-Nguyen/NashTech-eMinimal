package com.eminimal.backend.models.product;

import com.eminimal.backend.models.Category;
import lombok.*;
import javax.persistence.*;
import java.util.*;

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

}