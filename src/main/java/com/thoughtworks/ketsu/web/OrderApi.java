package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("users/{userId}/orders")
public class OrderApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(@PathParam("userId") long userId) {
        return Response.created(URI.create("")).build();
    }
}
