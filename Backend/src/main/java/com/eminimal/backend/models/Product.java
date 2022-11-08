package com.eminimal.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @OneToMany
    @ToString.Exclude
    private List<Rating> productRating = new ArrayList<>();

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

    public Product(String productName, String productDesc, List<String> productImage, float productCost, int productAmount, Category categories) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productAmount = productAmount;
        this.categories = categories;
    }

    public Product(String productName, String productDesc, List<String> productImage, float productCost, int productAmount, int productSale, Date dateSale, Category categories) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productAmount = productAmount;
        this.productSale = productSale;
        this.dateSale = dateSale;
        this.categories = categories;
    }

    public Product(String productID, String productName, String productDesc, float productCost, Category categories) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCost = productCost;
        this.categories = categories;
    }
}