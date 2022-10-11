public class Category {
    private String categoryID;
    private String categoryName;
    private String categoryDesc;

    public Category() {}

    public Category(String categoryName, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Category{" +
                "categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                '}';
    }
}