import java.util.Date;

public class Product {
    private String productID;
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
    private String categoryID;

    public Product() {}

    public Product(String productName, String productDesc, String productImage, float productCost, int productSale, int productRating, int productAmount, Date dateCreate, Date dateUpdate, Date dateSale, String categoryID) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productSale = productSale;
        this.productRating = productRating;
        this.productAmount = productAmount;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.dateSale = dateSale;
        this.categoryID = categoryID;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public float getProductCost() {
        return productCost;
    }

    public void setProductCost(float productCost) {
        this.productCost = productCost;
    }

    public int getProductSale() {
        return productSale;
    }

    public void setProductSale(int productSale) {
        this.productSale = productSale;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productCost=" + productCost +
                ", productSale=" + productSale +
                ", productRating=" + productRating +
                ", productAmount=" + productAmount +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                ", dateSale=" + dateSale +
                ", categoryID='" + categoryID + '\'' +
                '}';
    }
}