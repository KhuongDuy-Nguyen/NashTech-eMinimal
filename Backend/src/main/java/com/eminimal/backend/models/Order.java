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
@RequiredArgsConstructor
public class Order {
    @Id
    private UUID orderID;

    private int amount;
    private float price;

    @OneToMany
    @JoinColumn(name = "productID")
    private List<Product> product = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return orderID != null && Objects.equals(orderID, order.orderID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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