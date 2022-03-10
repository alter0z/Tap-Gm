package com.gmash.tapgm.models;

public class ProductModel {
    String visitor,owner;

    public ProductModel() {
    }

    public ProductModel(String visitor, String owner) {
        this.visitor = visitor;
        this.owner = owner;
    }

    public String getVisitor() {
        return visitor;
    }

    public String getOwner() {
        return owner;
    }
}
