package com.eminimal.backend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "orderID"))
public class Order {
    @Id
    private UUID orderID = UUID.randomUUID();

    private int amount;
    private float price;

    @ManyToMany
    @ToString.Exclude
    private Collection<Product> products;


}