package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "order_id"))
public class Orders {
    @Id
    @Column(name = "order_id")
    private UUID orderID = UUID.randomUUID();

    private int amount;
    private float price;

    @ManyToMany
    @ToString.Exclude
    private Collection<Product> products;

}