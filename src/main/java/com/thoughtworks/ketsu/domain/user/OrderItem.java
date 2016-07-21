package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class OrderItem implements Record{
    private long productId;
    private int quantity;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("product_id", getProductId());
            put("quantity", getQuantity());
            put("amount", getAmount());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
