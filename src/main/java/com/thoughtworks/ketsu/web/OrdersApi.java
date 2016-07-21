package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validators.OrderValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Map;

public class OrdersApi {

    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(Map orderInfo,
                               @Context Routes routes,
                               @Context OrderValidator validator) {
        validator.validate(Arrays.asList("name", "address", "phone", "order_items"), orderInfo);

        user.placeOrder(orderInfo);
        return Response.created(routes.orderUrl(user.getId(), Long.valueOf(orderInfo.get("id").toString()))).build();
    }

    @Path("{id}")
    public OrderApi getOrder(@PathParam("id") String id) {
        return new OrderApi(new Order());
    }
}
