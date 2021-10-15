package com.gmash.tapgm.models;

public class OwnerProductModel {
    String product,category,stock,price;

    public OwnerProductModel() {
    }

    public OwnerProductModel(String product, String category, String stock, String price) {
        this.product = product;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
