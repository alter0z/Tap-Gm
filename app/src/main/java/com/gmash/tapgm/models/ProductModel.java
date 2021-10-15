package com.gmash.tapgm.models;

public class ProductModel {
    String visitor,product,owner,stock,price;

    public ProductModel() {
    }

    public ProductModel(String visitor, String product, String owner, String stock, String price) {
        this.visitor = visitor;
        this.product = product;
        this.owner = owner;
        this.stock = stock;
        this.price = price;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
