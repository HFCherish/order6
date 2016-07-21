package com.thoughtworks.ketsu.domain.user;

import org.joda.time.DateTime;

public class Payment {
    private long orderId;
    private PayType type;
    private double amount;
    private DateTime createdAt;

    public long getOrderId() {
        return orderId;
    }

    public PayType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
