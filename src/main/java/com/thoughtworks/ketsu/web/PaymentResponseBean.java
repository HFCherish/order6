package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Payment;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class PaymentResponseBean implements Record{
    private Payment payment;
    private UriInfo uriInfo;

    public PaymentResponseBean(Payment payment, UriInfo uriInfo) {
        this.payment = payment;
        this.uriInfo = uriInfo;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        String payPath = uriInfo.getPath();
        return new HashMap<String, Object>() {{
            put("order_uri", payPath.substring(0, payPath.length()-"/payment".length()));
            put("uri", payPath);
            put("pay_type", payment.getType());
            put("amount", payment.getAmount());
            put("created_at", payment.getCreatedAt());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
