package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart" ,uniqueConstraints = @UniqueConstraint(columnNames = "cart_id"))
public class Cart {

    @Id
    @Column(name = "cart_id")
    private String cartID =  UUID.randomUUID().toString();

    private int cartQuantity;
    private float price;
    private boolean cartStatus = false;

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