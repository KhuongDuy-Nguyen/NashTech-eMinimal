public class Order {
    private String orderID;
    private int ammount;
    private float price;
    private String productID;
    private String userID;

    public Order() {}

    public Order(int ammount, float price, String productID, String userID) {
        this.ammount = ammount;
        this.price = price;
        this.productID = productID;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
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
                ", ammount=" + ammount +
                ", price=" + price +
                ", productID='" + productID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}