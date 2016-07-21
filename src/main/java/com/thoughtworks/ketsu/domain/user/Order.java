package com.thoughtworks.ketsu.domain.user;

import java.util.List;

public class Order {
    private long id;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public long getId() {
        return id;
    }
}
