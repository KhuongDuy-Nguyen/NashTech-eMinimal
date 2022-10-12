package com.eminimal.backend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product {
    @Id
    private UUID productID;

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
    private String categoryID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productID != null && Objects.equals(productID, product.productID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}