package com.eminimal.backend.models;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Category categories;


}