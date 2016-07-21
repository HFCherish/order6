package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users/{userId}/orders")
public class OrderApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(@PathParam("userId") long userId,
                               @Context Routes routes) {
        return Response.created(routes.orderUrl(userId, 7809l)).build();
    }
}
