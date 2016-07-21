package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Map userInfo) {
        if (userInfo.get("name") == null || !userInfo.get("name").toString().matches("^[a-zA-Z\\d]+$"))
            throw new IllegalArgumentException("must contains name composed of letters and numbers.");
        return Response.created(URI.create("")).build();
    }
}
