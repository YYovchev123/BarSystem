package models;

import java.util.ArrayList;

public class Order {
    private String uid;
    private int tableNumber;
    private ArrayList<Product> products;
    private int discountPercent;

    public Order(String uid, int tableNumber, ArrayList<Product> products) {
        this.uid = uid;
        this.tableNumber = tableNumber;
        this.products = products;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Product product : this.products) {
            totalAmount += product.getTotalPrice();
        }
        if(this.discountPercent > 0) {
            return totalAmount - ((this.discountPercent / 100) * totalAmount);
        }
        return totalAmount;
    }

    public String getTotalAmountString() {
        double totalAmount = getTotalAmount();
        return Double.toString(totalAmount) + " лв.";
    }

    public String getProductsCount() {
        int productsCount = 0;
        for(Product product : this.products) {
            productsCount = productsCount + product.getCount();
        }
        return Integer.toString(productsCount);
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
