package models;

public class Category {
    public ProductType type;
    public String title;

    public Category(ProductType type, String title) {
        this.type = type;
        this.title = title;
    }
}
