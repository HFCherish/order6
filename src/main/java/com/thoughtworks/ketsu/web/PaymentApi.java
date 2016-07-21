package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.web.validators.NotNullValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map payInfo) {
        new NotNullValidator().validate(Arrays.asList("pay_type", "amount"), payInfo);
        return Response.created(URI.create("")).build();
    }
}
