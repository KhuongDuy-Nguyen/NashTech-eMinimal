package com.eminimal.backend.models;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "productID"))
public class Product {
    @Id
    private UUID productID = UUID.randomUUID();

    private String productName ;
    private String productDesc ;
    private String productImage ;
    private float  productCost;
    private int productSale ;
    private int productRating ;
    private int productAmount ;
    private Date dateCreate ;
    private Date dateUpdate ;
    private Date dateSale ;

    @ManyToOne
    private Category categoryID;

    public Product() {}

    public Product(String productName, String productDesc, String productImage, float productCost, int productSale, int productRating, int productAmount, Date dateCreate, Date dateUpdate, Date dateSale, Category categoryID) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productSale = productSale;
        this.productRating = productRating;
        this.productAmount = productAmount;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.dateSale = dateSale;
        this.categoryID = categoryID;
    }

}