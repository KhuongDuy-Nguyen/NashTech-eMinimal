package com.eminimal.backend.models;

public class Order {
    private String orderID;
    private int amount;
    private float price;
    private String productID;
    private String userID;

    public Order() {}

    public Order(int amount, float price, String productID, String userID) {
        this.amount = amount;
        this.price = price;
        this.productID = productID;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", productID='" + productID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}