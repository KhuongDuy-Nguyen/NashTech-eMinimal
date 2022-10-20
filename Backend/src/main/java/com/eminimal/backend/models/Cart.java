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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cart_id"))
public class Cart {

    @Id
    @Column(name = "cart_id")
    private UUID cartID =  UUID.randomUUID();

    private int cartQuantity;
    private float price;

    @Column(columnDefinition = "boolean default true")
    private boolean cartStatus;

    @ManyToMany
    @ToString.Exclude
    private List<Product> cartProducts = new ArrayList<>();

    @ManyToOne
    private Users cartUsers;

    public Cart(List<Product> products, Users users) {
        this.cartProducts = products;
        this.cartUsers = users;
    }
}