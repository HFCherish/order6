package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import java.util.*;
import java.util.stream.Collectors;

public class Order implements Record {
    private long id;
    private long userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;
    private DateTime createdAt;

    public long getUserId() {
        return userId;
    }

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

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        List items = orderItems.stream().map(item -> item.toJson(routes)).collect(Collectors.toList());
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(getUserId(), getId()));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("total_price", getTotalPrice());
            put("created_at", getCreatedAt());
            put("order_items", items);
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getAmount() * item.getQuantity();
        }
        return total;
    }
}
