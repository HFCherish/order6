package com.thoughtworks.ketsu.domain.product;

public class Product {
    private long id;
    private String name;
    private String description;
    private double price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
