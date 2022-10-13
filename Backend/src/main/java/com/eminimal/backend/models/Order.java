package com.eminimal.backend.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "orderID"))
public class Order {
    @Id
    private UUID orderID = UUID.randomUUID();

    private int amount;
    private float price;

    @ManyToMany
    @JoinColumn(name = "productID")
    private List<Product> product = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "userID")
    private List<User> userID = new ArrayList<>();

    public Order() { }
    public Order(int amount, float price, List<Product> product, List<User> userID) {
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "orderID = " + orderID + ", " +
                "amount = " + amount + ", " +
                "price = " + price + ", " +
                "userID = " + userID + ")";
    }
}