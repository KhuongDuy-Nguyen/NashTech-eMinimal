package com.eminimal.backend.models;

import com.eminimal.backend.models.product.Product;
import com.eminimal.backend.models.users.Users;
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